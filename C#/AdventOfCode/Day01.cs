using System.Collections;
using System.Collections.Generic;
using System.Data;

namespace AdventOfCode;

public class Day01 : BaseDay
{
    private readonly List<String> line;
    private readonly List<int> left;
    private readonly List<int> right;

    public Day01()
    {
        line = File.ReadAllText(InputFilePath).Split('\n').ToList();
        left = new List<int>();
        right = new List<int>();

        foreach (var item in line)
        {
            left.Add(Int32.Parse(item.Split("   ")[0]));
            right.Add(Int32.Parse(item.Split("   ")[1].Split('\r')[0]));
        }
    }

    public int part1_delta() { 
        left.Sort();
        right.Sort();
        int delta = 0;
        for (int i = 0; i < left.Count; i++)
        {
            delta += Math.Abs(left[i] - right[i]);
        }
        return delta;
    }

    public int part2_delta()
    {
        Dictionary<int,int> leftDic = new Dictionary<int,int>();
        Dictionary<int,int> rightDic = new Dictionary<int,int>();

        for (int i = 0; i < left.Count; i++)
        {
            leftDic[left[i]] = leftDic.ContainsKey(left[i]) ? leftDic[left[i]] + 1 : 1;
            rightDic[right[i]] = rightDic.ContainsKey(right[i]) ? rightDic[right[i]] + 1 : 1;
        }

        int delta = 0;
        foreach (var leftItem in leftDic)
        {
            delta += rightDic.ContainsKey(leftItem.Key) ? rightDic[leftItem.Key] * leftDic[leftItem.Key] * leftItem.Key : 0; 
        }

        return delta;
    }
    public override ValueTask<string> Solve_1() => new($"{part1_delta()}");

    public override ValueTask<string> Solve_2() => new($"{part2_delta()}");
}
