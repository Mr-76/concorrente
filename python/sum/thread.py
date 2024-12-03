import threading
import time


sumLock = threading.Lock()

sum = 0

def sum1():
    count = 0
    for i in range(10000000):
        count += 1
    global sum
    with sumLock:
        sum += count

def sum2():
    count = 0
    for i in range(10000000):
        count += 1
    global sum
    with sumLock:
        sum += count



if __name__ == "__main__":
    start_time = time.time()
    
    thread1 = threading.Thread(target=sum1)
    thread2 = threading.Thread(target=sum2)

    thread1.start()
    thread2.start()

    thread1.join()
    thread2.join()

    end_time = time.time()

    print(f"Time taken: {end_time - start_time} seconds")
    print(f"sum value {sum}")
