using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using AdventOfCode2018.Day7;
using Shouldly;
using Xunit;

namespace AdventOfCode2018Tests.Day7
{
    public class StepOrdererTests
    {
        private StepOrderer stepOrderer { get; set; }

        [Fact]
        public void test_sample_input_1()
        {
            var input = new List<string>
            {
                "Step C must be finished before step A can begin.",
                "Step C must be finished before step F can begin.",
                "Step A must be finished before step B can begin.",
                "Step A must be finished before step D can begin.",
                "Step B must be finished before step E can begin.",
                "Step D must be finished before step E can begin.",
                "Step F must be finished before step E can begin."
            };

            stepOrderer = new StepOrderer();
            stepOrderer.LoadInput(input);

            stepOrderer.Run().ShouldBe("CABDFE");
        }

        [Fact]
        public void puzzle_one()
        {
            var input = File.ReadLines(@"Day7\Input.txt").ToList();

            stepOrderer = new StepOrderer();
            stepOrderer.LoadInput(input);

            File.WriteAllText(@"C:\Temp\aoc.txt", stepOrderer.Run());
        }

        [Fact]
        public void test_GetScoreForLetter()
        {
            StepOrderer.GetScoreForLetter('a', false).ShouldBe(1);
            StepOrderer.GetScoreForLetter('d', false).ShouldBe(4);
            StepOrderer.GetScoreForLetter('z', false).ShouldBe(26);
        }

        [Fact]
        public void test_sample_input_2()
        {
            var input = new List<string>
            {
                "Step C must be finished before step A can begin.",
                "Step C must be finished before step F can begin.",
                "Step A must be finished before step B can begin.",
                "Step A must be finished before step D can begin.",
                "Step B must be finished before step E can begin.",
                "Step D must be finished before step E can begin.",
                "Step F must be finished before step E can begin."
            };

            stepOrderer = new StepOrderer();
            stepOrderer.LoadInput(input, false);

            stepOrderer.RunInSync(2, false).ShouldBe(15);
        }

        [Fact]
        public void puzzle_2()
        {
            var input = File.ReadLines(@"Day7\Input.txt").ToList();

            stepOrderer = new StepOrderer();
            stepOrderer.LoadInput(input);

            stepOrderer.RunInSync(5).ShouldBe(880);
        }
    }
}
