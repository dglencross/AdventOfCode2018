using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace AdventOfCode2018.Day7
{
    public class Step
    {
        public string Id { get; set; }

        public int Seconds { get;set; }

        public bool IsAvailable(List<Step> steps)
        {
            var stillToDoIds = steps.Select(x => x.Id).ToArray();
            foreach (var dependency in Dependencies)
            {
                if (stillToDoIds.Contains(dependency))
                {
                    return false;
                }
            }

            return true;
        }

        public List<string> Dependencies { get; set; }
    }
}
