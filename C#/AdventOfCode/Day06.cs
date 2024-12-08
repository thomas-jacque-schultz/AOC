using System.Collections;
using System.Collections.Generic;
using System.Data;
using System.Drawing;
using System.Linq.Expressions;
using System.Numerics;
using System.Runtime.Serialization.Formatters;
using System.Text.RegularExpressions;

namespace AdventOfCode;

public class Day06 : BaseDay
{
    private List<List<char>> lines = new List<List<char>>();
    private List<Point> hit = new List<Point>();

    private List<string> collision = new List<string>();
    Point currentP = new Point();
    Point start = new Point();

    bool cycling = false;

    private string facing = "";
    private string facingStart = "";

    public Day06()
    {
        lines = File.ReadAllText(InputFilePath).Split("\r\n").Select(elem => elem.ToCharArray().ToList()).ToList();
        for (int i = 0; i < lines.Count; i++)
        {
            for (int j = 0; j < lines[i].Count; j++)
            {
                switch (lines[i][j])
                {
                    case '>':
                        facing = "r";
                        facingStart = "r";
                        start = new Point(i, j);
                        currentP = new Point(i, j);
                        break;
                    case '^':
                        facing = "u";
                        facingStart = "u";
                        start = new Point(i, j);
                        currentP = new Point(i, j);
                        break;
                    case 'v':
                        facing = "d";
                        facingStart = "d";
                        start = new Point(i, j);
                        currentP = new Point(i, j);
                        break;
                    case '<':
                        facing = "l";
                        facingStart = "l";
                        start = new Point(i, j);
                        currentP = new Point(i, j);
                        break;
                }
            }
        }
    }

    public int part1_delta()
    {
        while (isInside(currentP.X, currentP.Y))
        {
            lines[currentP.X][currentP.Y] = 'X';
            currentP = nextPosition(currentP);
        }

        int count = 0;
        foreach (var item in lines)
        {
            foreach (var item1 in item)
            {
                if (item1 == 'X')
                {
                    count++;
                }
            }
        }

        return count;
    }

    public Point nextPosition(Point p)
    {
        Point newPoint = new Point();

        bool collisionOccur = false;
        switch (facing)
        {
            case "u":
                if (collide(p.X - 1, p.Y))
                {
                    collisionOccur = true;
                    if (collision.Contains(p.X + "," + p.Y + "u"))
                    {
                        cycling = true;
                    }
                    collision.Add(p.X + "," + p.Y+"u");
                    facing = "r";
                }
                break;
            case "l":
                if (collide(p.X, p.Y - 1))
                {
                    collisionOccur = true;
                    if (collision.Contains(p.X + "," + p.Y + "l"))
                    {
                        cycling = true;
                    }
                    collision.Add(p.X + "," + p.Y + "l");

                    facing = "u";
                }
                break;
            case "d":
                if (collide(p.X + 1, p.Y))
                {
                    collisionOccur = true;
                    if (collision.Contains(p.X + "," + p.Y + "d"))
                    {
                        cycling = true;
                    }
                    collision.Add(p.X + "," + p.Y + "d");

                    facing = "l";
                }
                break;
            case "r":
                if (collide(p.X, p.Y + 1))
                {
                    collisionOccur = true;
                    if (collision.Contains(p.X + "," + p.Y + "r"))
                    {
                        cycling = true;
                    }
                    collision.Add(p.X + "," + p.Y + "r");

                    facing = "d";
                }
                break;
        }

        if (collisionOccur)
        {
            hit.Add(new Point(p.X, p.Y));
        }


        switch (facing)
        {
            case "u":
                newPoint = new Point(p.X - 1, p.Y);
                break;
            case "l":
                newPoint = new Point(p.X, p.Y - 1);
                break;
            case "d":
                newPoint = new Point(p.X + 1, p.Y);
                break;
            case "r":
                newPoint = new Point(p.X, p.Y + 1);
                break;
        }

        return newPoint;
    }

    public bool isInside(int x, int y)
    {
        return x < lines.Count && x >= 0 && y < lines[0].Count && y >= 0;
    }

    public bool collide(int x, int y)
    {
        if (isInside(x, y) && lines[x][y] == '#')
        {
            return true;
        }
        return false;
    }

    public int part2_delta()
    {
        int loopQ = 0;
        int a = 20000;
        for (int i = 0; i < lines.Count; i++)
        {
            for (int j = 0; j < lines[0].Count; j++)
            {
                if (lines[i][j] == '#' || lines[i][j] =='.')
                {
                    continue;
                }
                collision = new List<string>();
                cycling = false;

                lines[i][j] = '#';

                currentP = start;
                facing = facingStart;

                int step = 0;
                while (isInside(currentP.X, currentP.Y) && !cycling)
                {
                    currentP = nextPosition(currentP);
                    step++;
                }
                if (cycling)
                {
                    loopQ++;
                }

                step = 0;
                lines[i][j] = 'X';


            }
        }
        return loopQ;
    }


    public override ValueTask<string> Solve_1() => new($"{part1_delta()}");

    public override ValueTask<string> Solve_2() => new($"{part2_delta()}");
}
