def is_perfect_number(n):
    if n == 6 or n == 28 or n == 496 or n == 8128 or n == 33550336:  # known perfect numbers
        return True
    elif n % 2 == 1:
        return False
    else:
        p = 2
        while (2**p - 1) * (2**(p-1)) <= n:
            if n == (2**p - 1) * (2**(p-1)):
                return True
            p += 1
        return False

n = int(input())
for i in range(n):
    x = int(input())
    if is_perfect_number(x):
        print("YES, {} is a perfect number!".format(x))
    else:
        print("NO, {} is not a perfect number!".format(x))