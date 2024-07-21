package CodeAlpha_Stock_Trading_Platform;

import java.util.*;

public class CodeAlpha_Stock_Trading_Platform{

    //stockmarket info
    static class StockMarket{
        private HashMap<String , Double> stocks;
        public StockMarket(){
            stocks = new HashMap<>();
        }
         
        //add a new stock
        public void addStock(String stockName, double price){
            stocks.put(stockName, price);
            System.out.println("stock added with name : " + stockName);
        }

        //get stock price
        public double getStockPrice(String stockName){
            if (!stocks.containsKey(stockName)){
                System.out.println(stockName + " not found");
                return 0.0;
            }
            else{
                return stocks.get(stockName);
            }
        }

        //change stock price
        public void changeStockPrice(String stockName , double newPrice){
            stocks.put(stockName, newPrice);
        }

        //delete stock
        public void deleteStock(String stockName){
            if (!stocks.containsKey(stockName)){
                System.out.println(stockName + " not found");
            }
            else{
                stocks.remove(stockName);
                System.out.println(stockName + " deleted");
            }
        }

        //print all available stocks
        public void printAllStocks(){
            Set<String> keySet = stocks.keySet();
            for (String key : keySet){
                System.out.println(key + " : " + stocks.get(key));
            }
        }
    }

    static class Info{
        private double balance;
        private HashMap<String, Integer> portfolio;//stockname , qty of that stock
        public Info(double balance){
            this.balance = balance;
            portfolio = new HashMap<>();
        }
    }

    //user info
    static class User{
        private StockMarket stock;
        private HashMap<String, Info> users;
        public User(){
            users = new HashMap<>();
            stock = new StockMarket();
        }

        //add your account 
        public void addUser(String name, double balance){
            users.put(name, new Info(balance));
            System.out.println("Account added with name : " + name);
        }

        //get balance 
        public double getBalance(String name){
            if (!users.containsKey(name)){
                System.out.println(name + " not found");
                return 0.0;
            }
            else{
                return users.get(name).balance;
            }
        }

        //change balance
        public void changeBalance(String name, double newBalance){
            if (!users.containsKey(name)){
                System.out.println(name + " not found");
            }
            else{
                users.get(name).balance = newBalance;
            }
        }

        //get portfolio
        public HashMap<String , Integer> getPortfolio(String name){
            if (!users.containsKey(name)){
                System.out.println(name + " not found");
                return new HashMap<>();
            }
            else{
                return users.get(name).portfolio;
            }
        }

        //buy stock
        public void buyStock(String name, String stockSymbol, int qty){
            double price = stock.getStockPrice(stockSymbol);
            if (!users.containsKey(name)){
                System.out.println(name + " not found");
            }
            else{
                if (users.get(name).balance > price * qty){
                    users.get(name).balance -= price* qty;
                    users.get(name).portfolio.put(stockSymbol, users.get(name).portfolio.getOrDefault(stockSymbol, 0) + qty);
                    System.out.println(stockSymbol + " bought");
                }
                else{
                    System.out.println("Not enough balance to buy stocks");
                }
            }
        }

        //sell stocks
        public void sellStock(String name, String stockSymbol, int qty){
            double price = stock.getStockPrice(stockSymbol);
            if (!users.containsKey(name)){
                System.out.println(name + " not found");
            }
            else{
                if (users.get(name).portfolio.get(stockSymbol) >= qty){
                    users.get(name).balance += price* qty;
                    users.get(name).portfolio.put(stockSymbol, users.get(name).portfolio.get(stockSymbol) - qty);
                    System.out.println(stockSymbol + " selled");
                }
                else{
                    System.out.println("Not enough stocks to sell");
                }
            }
        }

        //print info about user
        public void printInfo(String name){
            if (!users.containsKey(name)){
                System.out.println(name + " not found");
            }
            else{
                System.out.println("username : " + name);
                System.out.println("balance amount : " + users.get(name).balance);
                Set<String> keyset = users.get(name).portfolio.keySet();
                for (String key : keyset){
                    System.out.println(key + " : " + users.get(name).portfolio.get(key));
                }
            }
        }

        //delete user account
        public void deleteAccount(String name){
            users.remove(name);
            System.out.println("Account deleted with name : " + name);
        }
    }
    

    public static void main(String[] args) {
        User users = new User();
        users.addUser("Abc", 100000);
        users.addUser("xyz", 50000);
        users.stock.addStock("Apple", 10000);
        users.stock.addStock("CodeAlpha", 5000);
        users.stock.addStock("Google", 7000);
        users.buyStock("Abc", "Apple", 5);
        users.buyStock("Abc", "Google", 2);
        users.buyStock("Abc", "CodeAlpha", 3);
        users.buyStock("Def", "Apple", 5);
        users.buyStock("xyz", "CodeAlpha", 7);
        users.buyStock("xyz", "Google", 10);
        users.sellStock("Abc", "Apple", 4);
        System.out.println(users.getPortfolio("Abc"));
        users.printInfo("Abc");
        users.deleteAccount("xyz");
        users.stock.deleteStock("Google");
        //CodeAlpha_Stock_Trading_Platform
    }
}