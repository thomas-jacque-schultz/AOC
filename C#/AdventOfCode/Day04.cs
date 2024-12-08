using System.Collections;
using System.Collections.Generic;
using System.Data;
using System.Drawing;
using System.Numerics;
using System.Runtime.Serialization.Formatters;
using System.Text.RegularExpressions;

namespace AdventOfCode;

public class Day04 : BaseDay
{
    private readonly List<List<char>> lines = new List<List<char>>();
    private List<Point> points = new List<Point>();

    public Day04()
    {
        foreach (var line in File.ReadAllText(InputFilePath).Split('\n'))
        {
            lines.Add(line.ToList());
        }
    }

    public int part1_delta()
    {
        int xmaxCount = 0;

        for (int i = 0; i < lines.Count; i++)
        {
            for (int j = 0; j < lines[i].Count; j++)
            {
                if (lines[i][j] == 'X')
                {
                    Point item = new Point(i, j);
                    if (searchDirection(item, 0, 1))
                    {
                        xmaxCount++;
                    }
                    if (searchDirection(item, 1, 0))
                    {
                        xmaxCount++;
                    }
                    if (searchDirection(item, -1, 0))
                    {
                        xmaxCount++;
                    }
                    if (searchDirection(item, 0, -1))
                    {
                        xmaxCount++;
                    }
                    if (searchDirection(item, 1, 1))
                    {
                        xmaxCount++;
                    }
                    if (searchDirection(item, -1, -1))
                    {
                        xmaxCount++;
                    }
                    if (searchDirection(item, 1, -1))
                    {
                        xmaxCount++;
                    }
                    if (searchDirection(item, -1, 1))
                    {
                        xmaxCount++;
                    }
                }
            }
        }
        return xmaxCount;
    }

    public bool searchDirection(Point p,int xDelta, int yDelta)
    {
        if( ! isInside (new Point(p.X+xDelta*3,p.Y+yDelta*3) ))
        {
            return false;
        }
        if (lines[p.X + xDelta * 1][p.Y + yDelta*1] == 'M' &&
            lines[p.X + xDelta * 2][p.Y + yDelta * 2] == 'A' &&
            lines[p.X + xDelta * 3][p.Y + yDelta * 3] == 'S')
        {
            return true;
        }
        return false;
    }

    public bool isInside(Point p)
    {
        return p.X < lines.Count && p.X >= 0 && p.Y < lines[0].Count && p.Y>=0 ;
    }

    public int part2_delta()
    {
        int xmaxCount = 0;

        for (int i = 1; i < lines.Count-1; i++)
        {
            for (int j = 1; j < lines[i].Count-1; j++)
            {
                if (lines[i][j] == 'A')
                {
                    if (lines[i - 1][j - 1] == 'S' && lines[i + 1][j + 1] == 'M'
                || lines[i - 1][j - 1] == 'M' && lines[i + 1][j + 1] == 'S')
                    {
                        if (lines[i - 1][j + 1] == 'S' && lines[i + 1][j - 1] == 'M'
                                        || lines[i - 1][j + 1] == 'M' && lines[i + 1][j - 1] == 'S')
                        {
                            xmaxCount++;
                        }
                    }
                }
            }
        }
        return xmaxCount;
    }


    public override ValueTask<string> Solve_1() => new($"{part1_delta()}");

    public override ValueTask<string> Solve_2() => new($"{part2_delta()}");
}
