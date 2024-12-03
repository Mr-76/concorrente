import threading
import requests
import threading
import urllib.request



barrier = threading.Barrier(2)

def download(url):
    print(f"starting to download {url}")
    barrier.wait()
    print("now starting after the barrier")
    response = urllib.request.urlopen(url)
    data = response.read()
    print(f"Finihed {url}")
    return data


def main():

    urls = [
        'https://www.ietf.org/rfc/rfc791.txt',
        'https://www.ietf.org/rfc/rfc792.txt',
        'https://www.ietf.org/rfc/rfc793.txt',
        'https://www.ietf.org/rfc/rfc794.txt',
        'https://www.ietf.org/rfc/rfc795.txt',
    ]

    # Create threads for each download
# create two threads
    threads = []
    for url in urls:
          thread = threading.Thread(target=download,args=(url,))
          threads.append(thread)
          thread.start()
    # Wait for all threads to complete
    for thread in threads:
        thread.join()


if __name__ == '__main__':
    main()
