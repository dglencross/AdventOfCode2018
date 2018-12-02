import unittest
import helpers.InputHelpers

def contains_exactly_x_of_any_letter(word, x):
    for letter in word:
        instances = word.count(letter)
        if instances == x:
            return True
    return False

def checksum(input):
    two_counts = 0
    three_counts = 0

    for i in input:
        if contains_exactly_x_of_any_letter(i, 2):
            two_counts += 1
        if contains_exactly_x_of_any_letter(i, 3):
            three_counts += 1

    return two_counts * three_counts


def words_differ_by_one(word1, word2):
    same_letters = []
    for x in range(0, len(word1)):
        if word1[x] == word2[x]:
            same_letters.append(word1[x])

    if len(same_letters) == len(word1) - 1:
        return same_letters
    return None


def search_all_words_for_matches(input):
    for i in input:
        for j in input:
            same_letters = words_differ_by_one(i, j)
            if same_letters != None:
                return same_letters


def run_puzzles():
    input = helpers.InputHelpers.read_input('input2.txt')
    result1 = checksum(input)
    print result1

    result2 = search_all_words_for_matches(input)

    formatted_result = ''
    for letter in result2:
        formatted_result += letter
    print formatted_result


if __name__ == '__main__':
    run_puzzles()

class Test_1(unittest.TestCase):

    def test_contains_none_of_the_same_letters(self):
        self.assertEquals(False, contains_exactly_x_of_any_letter('abcdef', 2))

    def test_contains_two_of_the_same_letter(self):
        self.assertEquals(True, contains_exactly_x_of_any_letter('bababc', 2))

    def test_contains_two_of_the_same_letter_two(self):
        self.assertEquals(True, contains_exactly_x_of_any_letter('abbcde', 2))

    def test_contains_three_but_not_two_of_the_same_letters(self):
        self.assertEquals(False, contains_exactly_x_of_any_letter('abcccd', 2))
        self.assertEquals(True, contains_exactly_x_of_any_letter('abcccd', 3))

    def test_checksum_should_match_sample_input(self):
        input = ['abcdef','bababc','abbcde','abcccd','aabcdd','abcdee','ababab']
        self.assertEquals(12, checksum(input))

    def test_puzzle_one(self):
        input = helpers.InputHelpers.read_input('input2.txt')
        self.assertEquals(8398, checksum(input))

class Test_2(unittest.TestCase):
    def test_completely_different_words_get_false(self):
        self.assertEquals(None, words_differ_by_one('abcde', 'fghij'))

    def test_two_letters_diff_still_gets_false(self):
        self.assertEquals(None, words_differ_by_one('abcde', 'axcye'))

    def test_similar_words_get_true(self):
        result = words_differ_by_one('fghij', 'fguij')
        self.assertEquals(4, len(result))

    def test_puzzle_two(self):
        input = helpers.InputHelpers.read_input('input2.txt')
        self.assertEquals(25, len(search_all_words_for_matches(input)))