
package main

import (
 "fmt"
 "math/rand"
)



func produce(out chan <- int) {
  rand.Seed(42)
  b := rand.Intn(5000)
  for i:= 0; i < b; i++{
	   v := rand.Intn(200000)
	   fmt.Println("produtor1 produz")
	   out <- v
  }

}



func consume(in <- chan int){
	for {
		value:= <- in
		if value % 2 == 0{
			fmt.Println("EH PAR",value) 
		}
	}

}

func main() {
	size := 1000
	ch := make(chan int,size)
	go produce(ch)
	go produce(ch)
	go consume(ch)
	for{}
}
