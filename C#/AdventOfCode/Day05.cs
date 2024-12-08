using System.Collections;
using System.Collections.Generic;
using System.Data;
using System.Drawing;
using System.Linq.Expressions;
using System.Numerics;
using System.Runtime.Serialization.Formatters;
using System.Text.RegularExpressions;

namespace AdventOfCode;

public class Day05 : BaseDay
{
    private readonly List<List<int>> rules= new List<List<int>>();
    private readonly List<List<int>> lines= new List<List<int>>();

    public Day05()
    {
        var w8 = File.ReadAllText(InputFilePath).Split("\n\r\n").ToList();
        rules.AddRange(w8[0].Split('\n').Select(elem => elem.Split('|').Select(elem => int.Parse(elem)).ToList()));
        lines.AddRange(w8[1].Split('\n').Select(elem => elem.Split(',').Select(elem => int.Parse(elem)).ToList()));
    }

    public int part1_delta()
    {

        int sum = 0;

        foreach (var item in lines)
        {
            if (respectAllRule(item))
            {
                sum += item[(item.Count ) / 2];
            }

        }
        return sum;
    }

    public bool respectAllRule(List<int> list)
    {
        foreach (var item in rules)
        {
            if (list.Contains(item[0]) && list.Contains(item[1]))
            {
                if (list.IndexOf(item[0]) > list.IndexOf(item[1]))
                {
                    return false;
                }
            }
        }
        return true;
    }

    public int part2_delta()
    {
        int sum = 0;
        foreach (var item in lines)
        {
            if (!respectAllRule(item))
            {
                forceRespectAllRule(item);
                Console.WriteLine();
                sum += item[(item.Count) / 2];
            }
        }
        return sum;
    }

    public void forceRespectAllRule(List<int> list)
    {
        while (!respectAllRule(list))
        {
            foreach (var item in rules)
            {
                if (list.Contains(item[0]) && list.Contains(item[1]))
                {
                    int indexPrevious = list.IndexOf(item[0]);
                    int indexAfter = list.IndexOf(item[1]);
                    if (indexPrevious > indexAfter)
                    {
                        int w8 = list[indexPrevious];
                        list[indexPrevious] = list[indexAfter];
                        list[indexAfter] = w8;
                    }
                }
            }
        }
    }


    public override ValueTask<string> Solve_1() => new($"{part1_delta()}");

    public override ValueTask<string> Solve_2() => new($"{part2_delta()}");
}
