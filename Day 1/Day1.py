import unittest
import helpers.InputHelpers

def puzzle_one(input):
    total = long(0)
    for line in input:
        total += long(line)
    return total

def puzzle_two(input, total, seen_numbers):
    for x in input:
        total += long(x)

        if total in seen_numbers:
            return total
        seen_numbers.append(total)

    return puzzle_two(input, total, seen_numbers)


def run_puzzles():
    input = helpers.InputHelpers.read_input('input1.txt')
    result1 = puzzle_one(input)
    print result1

    result2 = puzzle_two(input, 0, [0])
    print result2


if __name__ == '__main__':
    run_puzzles()

class Test_1(unittest.TestCase):

    def test_puzzle_one_can_add_properly(self):
        self.assertEquals(477, puzzle_one())

class Test_2(unittest.TestCase):
    def test_puzzle_two_passes_basic_example_one(self):
        input = ["+1", "-1"]

        self.assertEquals(0, puzzle_two(input, 0, [0]))

    def test_puzzle_two_passes_basic_example_two(self):
        input = ["+3", "+3", "+4", "-2", "-4"]

        self.assertEquals(10, puzzle_two(input, 0, [0]))

    def test_puzzle_two_passes_basic_example_three(self):
        input = ["-6", "+3", "+8", "+5", "-6"]

        self.assertEquals(5, puzzle_two(input, 0, [0]))

    def test_puzzle_two_passes_basic_example_four(self):
        input = ["+7", "+7", "-2", "-7", "-4"]

        self.assertEquals(14, puzzle_two(input, 0, [0]))

    def test_puzzle_two_is_ok(self):
        input = helpers.InputHelpers.read_input('input1.txt')

        self.assertEquals(390, puzzle_two(input, 0, [0]))