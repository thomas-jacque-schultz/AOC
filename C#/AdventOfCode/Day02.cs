using System.Collections;
using System.Collections.Generic;
using System.Data;

namespace AdventOfCode;

public class Day02 : BaseDay
{
    private readonly List<List<int>> lines = new List<List<int>>();

    public Day02()
    {
        foreach (var line in File.ReadAllText(InputFilePath).Split('\n'))
        {
            lines.Add(line.Split(" ").Select( x => int.Parse(x)).ToList());
        }
    }

    public int part1_delta()
    {
        int safeQuantity = 0;
        foreach (var line in lines)
        {
            int previous = line[0];
            safeQuantity += isSafeSpeed(line) ? 1 : 0;
        }
        return safeQuantity;
    }

    public int part2_delta()
    {
        int safeQuantity = 0;
        foreach (var line in lines)
        {

            bool oneIsSafe = false;
            for (int i = 0; i < line.Count; i++)
            {
                List<int> subList = line.ToList();
                subList.RemoveAt(i);
                oneIsSafe = oneIsSafe || isSafeSpeed(subList);
            }
            safeQuantity += oneIsSafe ? 1 : 0;
        }
        return safeQuantity;
    }

    public bool isSafeSpeed(List<int> intsLine)
    {
        int previous = intsLine[0];
        bool safe = true;
        for (int i = 1; i < intsLine.Count && safe; i++)
        {
            if (previous > intsLine[i] && (intsLine[i] + 1 <= previous && previous <= intsLine[i] + 3)) { }
            else
            {
                safe = false;
            }
            previous = intsLine[i];
        }
        if (safe)
        {
            return safe;
        }
        safe = true;
        for (int i = 1; i < intsLine.Count && safe; i++)
        {
            if (previous < intsLine[i] && (intsLine[i] >= previous + 1 && previous + 3 >= intsLine[i])) { }
            else
            {
                safe = false;
            }
            previous = intsLine[i];
        }
        return safe;
    }

    public bool isSafeBetter(List<int> intsLine)
    {
        if( intsLine.Zip(intsLine.Skip(1), (a, b) => (b - a) >= 1 && (b - a) <= 3).All( x => x) )  { return true; }
        if( intsLine.Zip(intsLine.Skip(1), (a, b) => (a - b) >= 1 && (a - b) <= 3).All( x => x) )  { return true; }
        return false;
    }

    public override ValueTask<string> Solve_1() => new($"{part1_delta()}");

    public override ValueTask<string> Solve_2() => new($"{part2_delta()}");
}
