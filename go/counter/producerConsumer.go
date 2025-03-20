package main


import (
    "fmt"
    "sync"
)


func main() {

	P := Producer{}
	C := Consumer{}
	msg := make(chan int,100)
		var wg sync.WaitGroup
	wg.Add(2)
	go P.produce(msg,&wg)
	go C.consume(msg,&wg)
	wg.Wait()

}


type Producer struct {

}

type Consumer struct {

}

func (p Producer) produce(ch chan int, wg *sync.WaitGroup){
	for i:=0; i<101;i++{
	fmt.Println("Producing")
	ch <- i
	}
	defer wg.Done()
}


func (c Consumer) consume(ch chan int, wg *sync.WaitGroup){
	for i:=0; i<101;i++{
		fmt.Println("Consuming")
		greeting := <- ch
		fmt.Println(greeting)
	}

	defer wg.Done()
}
