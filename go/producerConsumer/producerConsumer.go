package main


import (
    "fmt"
    "sync"
)


func main() {
	P := Producer{
		ID: "Producer1",
	}
	P2 := Producer{
		ID: "Producer2",
	}
	C := Consumer{
		ID: "Consumer",
	}
	msg := make(chan int,101)
	var wg sync.WaitGroup
	wg.Add(3)
	go P2.produce(msg,&wg)
	go P.produce(msg,&wg)
	go C.consume(msg,&wg)
	wg.Wait()

}


type Producer struct {
 ID string

}

type Consumer struct {
 ID string

}

func (p Producer) produce(ch chan int, wg *sync.WaitGroup){
	defer wg.Done()
	for i:=0; i<101;i++{
        fmt.Print(p.ID, " Producing",i,"\n")
	ch <- i
	}
}


func (c Consumer) consume(ch chan int, wg *sync.WaitGroup){

	defer wg.Done()
	for i:=0; i<101;i++{
		fmt.Println("Consuming")
		greeting := <- ch
		fmt.Print(c.ID, " Consuming",greeting,"\n")
	}

}
