using System;
using System.Collections.Generic;
using System.Linq;
using System.Linq.Expressions;

namespace AdventOfCode2018
{
    public class PolymerReducer
    {

        public string Reduce(string line)
        {
            var stringsToReplace = new List<string>();
            var output = string.Copy(line);

            for (int i = 0; i < line.Length - 1; i++)
            {
                var x = line[i];
                var y = line[i + 1];

                if (AreReplacementCandidates(x, y))
                {
                    stringsToReplace.Add($"{x}{y}");
                }
            }

            foreach (var stringToReplace in stringsToReplace)
            {
                output = output.Replace(stringToReplace, "");
            }

            return output;
        }

        public string ReduceCompletely(string line)
        {
            string output = null;

            while (null == output)
            {
                var previousLength = line.Length;
                line = Reduce(line);

                if (line.Length == previousLength)
                {
                    output = line;
                }
            }

            return output;
        }

        public string ReduceCompletelyAfterRemovingLetter(string line, string letterToRemove)
        {
            line = line.Replace(letterToRemove.ToUpper(), "").Replace(letterToRemove.ToLower(), "");

            return ReduceCompletely(line);
        }

        public int FindShortestResultRemovingOneLetter(string line)
        {
            var shortestLength = int.MaxValue;

            foreach (var letter in line.ToLower().Distinct())
            {
                var result = ReduceCompletelyAfterRemovingLetter(line, letter.ToString());

                if (result.Length < shortestLength)
                {
                    shortestLength = result.Length;
                }
            }

            return shortestLength;
        }

        public bool AreReplacementCandidates(char x, char y)
        {
            if (x.Equals(y))
            {
                return false;
            }

            if (x.ToString().ToLower().Equals(y.ToString().ToLower()))
            {
                return true;
            }

            return false;
        }

    }
}
