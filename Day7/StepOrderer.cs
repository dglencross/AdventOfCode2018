using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace AdventOfCode2018.Day7
{
    public class StepOrderer
    {
        public List<Step> Steps { get; set; }

        public Step PickNextStep()
        {
            return Steps.Where(x => x.IsAvailable(Steps)).OrderBy(x => x.Id).FirstOrDefault();
        }

        public string Run()
        {
            string result = "";
            while (Steps.Any())
            {
                var step = PickNextStep();
                result += step.Id;
                Steps.Remove(step);
            }

            return result;
        }

        public static int GetScoreForLetter(char letter, bool add60Seconds = true)
        {
            var result = 0;

            if (add60Seconds)
            {
                result += 60;
            }

            result += (int) letter % 32;

            return result;
        }

        public void LoadInput(List<string> input, bool add60Seconds = true)
        {
            Steps = new List<Step>();
            foreach (var i in input)
            {
                var line = i.Replace("Step ", "").Replace(" must be finished before step", "").Replace(" can begin.", "");

                var dependency = line.Split(' ')[0];

                if (line.Split(' ').Length < 2)
                {
                    var x = 1;
                }

                var dependant = line.Split(' ')[1];

                var dependantStep = Steps.Where(x => x.Id == dependant).FirstOrDefault();

                if (dependantStep == null)
                {
                    Steps.Add(new Step { Id = dependant, Dependencies = new List<string> { dependency }, Seconds = GetScoreForLetter(dependant[0], add60Seconds) });
                }
                else
                {
                    dependantStep.Dependencies.Add(dependency);
                }

                var dependencyStep = Steps.Where(x => x.Id == dependency).FirstOrDefault();

                if (dependencyStep == null)
                {
                    Steps.Add(new Step { Id = dependency, Dependencies = new List<string> { }, Seconds = GetScoreForLetter(dependency[0], add60Seconds)});
                }
            }
        }

        public int RunInSync(int threads, bool add60Seconds = true)
        {
            var seconds = 0;

            while (Steps.Any())
            {
                var nextSteps = Steps.Where(x => x.IsAvailable(Steps)).OrderBy(x => x.Seconds - GetScoreForLetter(x.Id[0], add60Seconds)).ThenBy(x => x.Id).Take(threads);

                foreach (var nextStep in nextSteps)
                {
                    nextStep.Seconds -= 1;

                    if (nextStep.Seconds == 0)
                    {
                        Steps.Remove(nextStep);
                    }
                }

                seconds += 1;
            }

            return seconds;
        }
    }
}
