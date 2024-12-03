import threading
import time

#shared object
class BankAccount:
    def __init__(self) -> None:
        self.balance = 0
        
    def deposit(self,amount,customer_name):
        print("%s Depositing amount : %d , Current Balance : %d" %(customer_name, amount,self.balance))
        time.sleep(10/1000)
        self.balance = self.balance + amount
        print("%s Checking Balance : its  %d" %(customer_name, self.balance))
    
#concurrent access: Thread
class CustomerThread(threading.Thread):
    def __init__(self, customer_name:str,account:BankAccount):
        super(CustomerThread, self).__init__()
        self.customer_name=customer_name
        self.account=account
    def run(self) -> None:
        self.account.deposit(1000,self.customer_name)
        return super().run()

#main program
account=BankAccount()
customer1=CustomerThread("Smith",account)
customer2=CustomerThread("Steves",account)
customer1.start()
customer2.start()
