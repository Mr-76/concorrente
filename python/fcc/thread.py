import threading
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
    
    thread1 = threading.Thread(target=count_up)
    thread2 = threading.Thread(target=count_down)

    thread1.start()
    thread2.start()

    thread1.join()
    thread2.join()

    end_time = time.time()

    print(f"Time taken: {end_time - start_time} seconds")
