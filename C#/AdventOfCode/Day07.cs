using System.Collections;
using System.Collections.Generic;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Linq.Expressions;
using System.Numerics;
using System.Runtime.Serialization.Formatters;
using System.Text.RegularExpressions;
using static System.Runtime.InteropServices.JavaScript.JSType;

namespace AdventOfCode;

public class Day07 : BaseDay
{
    private class Line{
        public long cible;
        public List<long> values;

        public Line(long cible, List<long> values)
        {
            this.cible = cible;
            this.values = values;
        }
    }


    private List<Line> lines = new List<Line>();
    private readonly Regex patternOperation = new Regex(@"(\d+)+", RegexOptions.Compiled);


    public Day07()
    {
        lines = File.ReadAllText(InputFilePath).Split("\r\n").Select(elem =>
        {
            List<Match> m = patternOperation.Matches(elem).ToList();
            return new Line(long.Parse(m[0].Value), m.Slice(1,m.Count-1).Select(elem => long.Parse(elem.Value)).ToList());
        }).ToList();
       
    }

    public long part1_delta()
    {
        long count = 0;
        foreach (var item in lines)
        {

            if (canDoIt(item, item.values[0],0))
            {
                count = count + item.cible;
            }
        }

        return count;
    }

    private bool canDoIt(Line line, long result, int rank)
    {
        rank++;
        if (rank>= line.values.Count)
        {
            return line.cible  == result ? true : false;
        }

        return canDoIt(line, result * line.values[rank],rank) || canDoIt(line, result + line.values[rank], rank);
       
    }

    private bool canDoItv2(Line line, long result, int rank)
    {
        rank++;
        if (rank >= line.values.Count)
        {
            return line.cible == result ? true : false;
        }
        if( result >= line.cible)
        {
            return false;
        }
        return canDoItv2(line, result * line.values[rank], rank) || canDoItv2(line, result + line.values[rank], rank) || canDoItv2(line, long.Parse($"{result}{line.values[rank]}") , rank);
    }


    public long part2_delta()
    {
        long count = 0;
        foreach (var item in lines)
        {
            if (canDoItv2(item, item.values[0], 0))
            {
                count = count + item.cible;
            }
        }

        return count;
    }


    public override ValueTask<string> Solve_1() => new($"{part1_delta()}");

    public override ValueTask<string> Solve_2() => new($"{part2_delta()}");
}

