package main

import (
 "fmt"
 "math/rand"
)



func produce(channel chan int) {
  rand.Seed(42)
  for i:= 0; i < 10000; i++{
   v := rand.Intn(100000000000000)
   channel <- v
  }

}

func consume(channel chan int){
	for {
		value:= <- channel
		if value % 2 == 0{
			fmt.Println(value)
		}

	}

}

func main() {
	ch := make(chan int)
	fmt.Printf("ehllo")
	go produce(ch)
	go consume(ch)
	for{}
}
