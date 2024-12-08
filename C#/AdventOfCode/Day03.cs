using System.Collections;
using System.Collections.Generic;
using System.Data;
using System.Numerics;
using System.Text.RegularExpressions;

namespace AdventOfCode;

public class Day03 : BaseDay
{
    private readonly String lines;
    private readonly Regex patternOperation = new Regex(@"mul\((\d+),(\d+)\)", RegexOptions.Compiled);

    public Day03()
    {
        lines = File.ReadAllText(InputFilePath);
    }

    public int part1_delta()
    {
        int answer = 0;
        MatchCollection stringOperation = patternOperation.Matches(lines);
        foreach (Match item in stringOperation)
        {
            int operand1 = int.Parse(item.Groups[1].Value);
            int operand2 = int.Parse(item.Groups[2].Value);
            answer += operand1 * operand2;
        }
        return answer;
    }

    public int part2_delta()
    {
        string patternDont = @"don\'t\(\).*?do\(\)";
        string dontlines = Regex.Replace(lines, patternDont,"", RegexOptions.Singleline);

        int answer = 0;
        string patternOperation = @"mul\([0-9]+\,[0-9]+\)";
        string patternDigit = @"[0-9]+";
        MatchCollection stringOperation = Regex.Matches(dontlines, patternOperation);
        foreach (var item in stringOperation)
        {

            List<Match> operande = Regex.Matches(item.ToString(), patternDigit).ToList();
            answer += int.Parse(operande[0].Value) * int.Parse(operande[1].Value);
        }
        return answer;
    }


    public override ValueTask<string> Solve_1() => new($"{part1_delta()}");

    public override ValueTask<string> Solve_2() => new($"{part2_delta()}");
}
