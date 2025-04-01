package main

import "fmt"

func main() {
    ch := make(chan int)

    // Enviar valores para o canal em uma goroutine
    go func() {
        for i := 0; i < 5; i++ {
            ch <- i
        }
        close(ch) // Fecha o canal após enviar todos os valores
    }()

    // Ler valores do canal
    for value := range ch {  // O loop 'range' vai até o canal ser fechado
        fmt.Println(value)
    }

    fmt.Println("Canal fechado. Não é possível mais enviar valores.")
}

