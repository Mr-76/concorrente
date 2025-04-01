
package main

import (
 "fmt"
 "math/rand"
 "time"
)

func request(out chan <- int) { 
	rand.Seed(666)
	r := 1
	time.Sleep(time.Duration(r) * time.Second)
	out <- r
}


func summer(in <- chan int,ngo int){
	soma := 0
	for i:=0 ;i<ngo;i++ {
	   value:= <- in
	   soma = soma + value
	}

	fmt.Println("soma eh ",soma)
}

func gateway(ngo int,wait_n int) {
	ch := make(chan int)
	for i:=0 ; i < ngo;i++{
	 go request(ch)
	}
	summer(ch,ngo)
}
func main() {
	gateway(10,100)
}
