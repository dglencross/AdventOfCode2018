using System.IO;
using System.Linq;
using AdventOfCode2018;
using Xunit;
using Shouldly;

namespace AdventOfCode2018Tests
{
    public class PolymerReducerTests
    {
        private PolymerReducer _reducer = new PolymerReducer();

        [Fact]
        public void test_x_and_X_are_candidates_for_replacement()
        {
            _reducer.AreReplacementCandidates('x', 'X').ShouldBeTrue();
        }

        [Fact]
        public void test_X_and_x_are_candidates_for_replacement()
        {
            _reducer.AreReplacementCandidates('X', 'x').ShouldBeTrue();
        }

        [Fact]
        public void test_x_and_x_are_not_candidates_for_replacement()
        {
            _reducer.AreReplacementCandidates('x', 'x').ShouldBeFalse();
        }

        [Fact]
        public void test_X_andX_are_not_candidates_for_replacement()
        {
            _reducer.AreReplacementCandidates('X', 'X').ShouldBeFalse();
        }

        [Fact]
        public void should_reduce_one_round_correctly()
        {
            _reducer.Reduce("dabAcCaCBAcCcaDA").ShouldBe("dabAaCBAcaDA");
        }

        [Fact]
        public void should_reduce_completely_correctly()
        {
            _reducer.ReduceCompletely("dabAcCaCBAcCcaDA").ShouldBe("dabCBAcaDA");
        }

        [Fact]
        public void puzzle_one()
        {
            var input = File.ReadLines(@"Day5\Input.txt").First();
            _reducer.ReduceCompletely(input).Length.ShouldBe(10250);
        }

        [Fact]
        public void should_reduce_completely_after_removing_one_line_correctly()
        {
            _reducer.ReduceCompletelyAfterRemovingLetter("dabAaBAaDA", "c").ShouldBe("daDA");
        }

        [Fact]
        public void should_find_shortest_length_removing_a_letter_first()
        {
            _reducer.FindShortestResultRemovingOneLetter("dabAcCaCBAcCcaDA").ShouldBe(4);
        }

        [Fact]
        public void puzzle_two()
        {
            var input = File.ReadLines(@"Day5\Input.txt").First();
            _reducer.FindShortestResultRemovingOneLetter(input).ShouldBe(6188);
        }
    }
}
