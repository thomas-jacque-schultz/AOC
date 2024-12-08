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

public class Day08 : BaseDay
{



    private List<List<char>> lines = new List<List<char>>();


    public Day08()
    {
        lines = File.ReadAllText(InputFilePath).Split("\r\n").Select(elem => elem.ToCharArray().ToList()).ToList();
       
    }

    public long part1_delta()
    {
        int count = 0;
        for (int i = 0; i < lines.Count; i++)
        {
            for (int j = 0; j < lines[0].Count; j++)
            {
                if (lines[i][j] != '.')
                {
                    count += allAntinodeForAntenna(lines[i][j],i,j);
                }
            }
        }
        return 0;
    }

    private int allAntinodeForAntenna(char c, int aI, int aJ)
    {
        int count = 0;

        for (int i = 0; i < lines.Count; i++)
        {
            for (int j = 0; j < lines[0].Count; j++)
            {
                if (lines[i][j] == c && (aI != i && aJ!=j))
                {
                }
            }
        }
        return count;
    }

    public bool isInside(Point p)
    {
        return p.X < lines.Count && p.X >= 0 && p.Y < lines[0].Count && p.Y >= 0;
    }


    public long part2_delta()
    {
        return 0;

    }


    public override ValueTask<string> Solve_1() => new($"{part1_delta()}");

    public override ValueTask<string> Solve_2() => new($"{part2_delta()}");
}

