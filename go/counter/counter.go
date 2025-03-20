package main



import (
    "fmt"
    "sync"
    "time"
)


func main() {
	msg := make(chan string)
	go greet(msg)
	time.Sleep(2 * time.Second)
	greeting := <- msg
	time.Sleep(2 * time.Second)
	fmt.Println("Greeting received")
	fmt.Println(greeting)

	_, ok := <- msg
	if ok {
		fmt.Println("CHANEL IS OPEN")
	}else{
		fmt.Println("CHANEL IS closed")
	}

//	var wg sync.WaitGroup 
//	wg.Add(2)
//	go helloworld(&wg)
//	go goodbye(&wg)
//	wg.Wait()
}




func greet(ch chan string){
	fmt.Println("Sending")
	ch <- "Hello world"
	close(ch)
	fmt.Println("Greeter sended")
}




func helloworld(wg *sync.WaitGroup) {
   defer wg.Done()
   fmt.Println("Hello world")
}


func goodbye(wg * sync.WaitGroup) {
   defer wg.Done()
   fmt.Println("bye")
}

