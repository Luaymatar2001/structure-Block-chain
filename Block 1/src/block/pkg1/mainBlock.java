/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package block.pkg1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Loay Matar
 */
public class mainBlock {
    private String transaction ;
  private String blockHash ;
  private String previousHash ;

    

    public mainBlock(String transaction,  String previousHash) {
        this.transaction = transaction;
        this.previousHash = previousHash;
        MessageDigest digest = null;
      try {
          digest = MessageDigest.getInstance("SHA-256");
      } catch (NoSuchAlgorithmException ex) {
          Logger.getLogger(mainBlock.class.getName()).log(Level.SEVERE, null, ex);
      }
byte[] hash1 = digest.digest(transaction.getBytes(StandardCharsets.UTF_8));
 this.blockHash= Base64.getEncoder().encodeToString(hash1);
//        this.blockHash = Arrays.hashCode(new int[]{Arrays.hashCode(transaction),this.previousHash});
        
    }



    public String toString() {
    return " Block [ transactions = "+transaction+"\n, blockHash = "+blockHash+"\n, previousHash= "+previousHash+" ] \n";
            
    }

    
    /**
     * @return the transaction
     */
    public String getTransaction() {
        return transaction;
    }

    /**
     * @param transaction the transaction to set
     */
    public void setTransaction(String transaction) {
        this.transaction = transaction;
    }

    /**
     * @return the blockHash
     */
    public String getBlockHash() {
        return blockHash;
    }

    /**
     * @param blockHash the blockHash to set
     */
    public void setBlockHash(String blockHash) {
        this.blockHash = blockHash;
    }

    /**
     * @return the previousHash
     */
    public String getPreviousHash() {
        return previousHash;
    }

    /**
     * @param previousHash the previousHash to set
     */
    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }
    static ArrayList<mainBlock> blockChain = new ArrayList<mainBlock>();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
          File file = new File("C:\\Users\\Loay Matar\\Documents\\NetBeansProjects\\Block 1\\src\\block\\pkg1\\Mining.txt");
             FileWriter fr = new FileWriter(file);
       String hash;
     String block1 = "shad has 700$ to Miguel has 550$";
     hash = mineBlock(block1 , "0" ,fr);

     String block2 = "Ahmed has 400$ to Iprahem has 250$";
    hash= mineBlock(block2 , hash,fr);
    
   String block3 = "Luay has 420$ to Matar has 950$";
    hash= mineBlock(block3 , hash ,fr);
    
   fr.close();
    System.out.println("the block 2 is : "+ blockExplorer(2));
    
     }
    
     public static String mineBlock(String Transaction , String prev ,FileWriter fr){
       String T = Transaction;
           mainBlock block = new mainBlock(T, prev);
           System.out.println(" Block : ");
             System.out.println("-------------------------------");
        System.out.println("-->the block befor Mining \n ");
        System.out.println(" Block is : "+block.toString());
     int count = 0;
        while (!block.getBlockHash().startsWith("0")) {
            T += count;
               block = new mainBlock(T, prev); 
            count++;
        }
           try {
      
    fr.write("\nthe block after Mining");
    fr.write("\nBlock[\n  transactions = "+Transaction
               +" \n , blockHash = "+block.getBlockHash()
               +" \n , previousHash= "+block.getPreviousHash()+"\n ] \n");
    fr.write("the Nonos : "+count+"\n");
       fr.write("______________________________\n");
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
           
      block.setTransaction(Transaction);
             blockChain.add(block);
             System.out.println("______________________________________");
      return block.getBlockHash();
        }
       
     
     //block Explorer
     public static String blockExplorer(int x){
        return "Block[transactions = "+blockChain.get(x).getTransaction()
                +" , blockHash = "+blockChain.get(x).getBlockHash()
                +", previousHash= "+blockChain.get(x).getPreviousHash()+"]";
     }
     
    private static final Logger LOG = Logger.getLogger(mainBlock.class.getName());
   
    
}


