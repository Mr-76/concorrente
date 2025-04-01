package main

import (
	"fmt"
	"math/rand"
	"time"
)

// Produz valores aleatórios e os envia para o canal
func produce(channel chan int, done chan bool) {
	rand.Seed(time.Now().UnixNano()) // Garante valores aleatórios diferentes a cada execução
	b := rand.Intn(10000)

	for i := 0; i < b; i++ {
		v := rand.Intn(200000)
		fmt.Println("Produtor 1 produz:", v)
		channel <- v
	}

	done <- true // Sinaliza que o produtor terminou
}

// Segundo produtor, funcionando da mesma forma
func produce2(channel chan int, done chan bool) {
	rand.Seed(time.Now().UnixNano()) 
	b := rand.Intn(10000)

	for i := 0; i < b; i++ {
		v := rand.Intn(10)
		fmt.Println("Produtor 2 produz:", v)
		channel <- v
	}

	done <- true // Sinaliza que o produtor terminou
}

// Consome valores do canal e imprime os números pares
func consume(channel chan int, done chan bool) {
	for value := range channel { // Continua lendo até o canal ser fechado
		if value%2 == 0 {
			fmt.Println("Consumidor recebeu:", value)
		}
	}
	done <- true // Sinaliza que terminou de consumir
}

func main() {
	ch := make(chan int)
	producersDone := make(chan bool, 2) // Buffer para 2 produtores
	done := make(chan bool)

	fmt.Println("Iniciando...")

	go produce(ch, producersDone)
	go produce2(ch, producersDone)
	go consume(ch, done)

	<-producersDone
	<-producersDone
	close(ch) // Fecha o canal para sinalizar que não haverá mais produção

	<-done // Espera o consumidor terminar antes de sair

	fmt.Println("Finalizado!")
}

