def read_input(filename):

    lines = []

    with open(filename) as fp:
        line = fp.readline()
        while line:
            lines.append(line)
            line = fp.readline()

    return lines