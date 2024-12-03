import time

sum = 0

def sum1():
    count = 0
    for i in range(10000000):
        global sum
        sum += 1
        count = count + i

def sum2():
    count = 0
    for i in range(10000000):
        global sum
        sum += 1
        count = count + i


if __name__ == "__main__":
    start_time = time.time()

    value1 = sum1()
    value2 = sum2()

    end_time = time.time()

    print(f"Time taken: {end_time - start_time} seconds")
    print(f"sum value {sum}")
