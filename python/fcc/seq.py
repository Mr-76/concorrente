import time

def count_up():
    count = 0
    for i in range(10000000):
        count = count + i

def count_down():
    count = 0
    for i in range(10000000):
        count = count + i

if __name__ == "__main__":
    start_time = time.time()

    count_up()
    count_down()

    end_time = time.time()

    print(f"Time taken: {end_time - start_time} seconds")
