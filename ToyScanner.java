import java.io.*;


public class ToyScanner {

	public static File fp;
	public static FileReader f;
	public static BufferedReader br;
	public static int charClass;
	public static int nextToken;
	public static int lexLen;
	public static char nextChar; 
	public static final int LETTER=0;
	public static final int DIGIT=1;
	public static final int UNKNOWN=99;
	public static final int IDENT=11;
	public static final int EOF=-1;
	public static final int ASSIGN_OP= 20;
	public static final int ADD_OP= 21;
	public static final int SUB_OP= 22;
	public static final int MULT_OP= 23;
	public static final int DIV_OP= 24;
	public static final int LEFT_PAREN= 25;
	public static final int RIGHT_PAREN =26;
	public static char[] lexeme=new char[100];
	public static void main(String args[]) throws IOException
	
	{
		
		try
		{
		 fp  = new File("C:/Users/gani/Desktop/Java workspace/ToyScan/InputFile.txt");
		}
		catch(Exception e)
		{
		
		System.out.println("File not found!");
			
		}
	     f=null;
		try {
			f = new FileReader(fp);
			br=new BufferedReader(f);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File not found");
			
		}
		getChar();
		do{
			nextToken=lex();
		}while(nextToken!=EOF);
		
		
	}
	public static int lex() throws IOException {
		// TODO Auto-generated method stub
		lexLen=0;
		getNonBlank();
		switch(charClass)
		{
        case LETTER :
            addChar();
            getChar();
           
            int i=0;
            while(charClass == LETTER || charClass == DIGIT)
            {
            	
                 addChar();
                 getChar();
                 i++;
            }
            nextToken =IDENT;
            break;
        case DIGIT :
            addChar();
            getChar();
           while(charClass == DIGIT)
           {
                addChar();
                getChar();
           }
           nextToken =IDENT;
           break;
        case UNKNOWN :
            nextToken=lookup(nextChar);
            getChar();
            if(nextToken==EOF)
            {
            	lex();
            	break;
		    }
            
            break;
        case EOF:
        	
            nextToken = EOF;
            lexeme[0] ='E';
            lexeme[1] ='O';
            lexeme[2] ='F';
            lexeme[3] = 0;
            return nextToken;
           }
		String s ="";
       int i=0;
		while(lexeme[i]!=0)
			
		{
			
			s=s+lexeme[i];
			i++;
		}
	System.out.println("Next token is :"+nextToken+"  Next lexeme is :"+s);	
	return nextToken;
		
	}
	public static int lookup(char nextChar2) {

		// TODO Auto-generated method stub
		switch(nextChar2)
	     {
	         case '(' :
	                    addChar();
	                    nextToken = LEFT_PAREN;
	                    break;
	         case ')' :
	                     addChar();
	                     nextToken = RIGHT_PAREN;
	                     break;
	         case '+' :
	                    addChar();
	                    nextToken = ADD_OP;
	                    break;	
	         case '-' :
	                    addChar();
	                    nextToken = SUB_OP;
	                    break;
	       

	     case '*' :
	                    addChar();
	                    nextToken = MULT_OP;
	                    break;
	     case '/' :
	                    addChar();
	                    nextToken = DIV_OP;
	                    break;
	     default:
	                   
	    	           
	    	           addChar();
	                   nextToken = EOF;
	                   break;
	     }
	     return nextToken;
	
		
		
		
		
	}
	public static void addChar() {
		// TODO Auto-generated method stub
		  if(lexLen <= 98)
		     {
		          lexeme[lexLen++] = nextChar;
		          lexeme[lexLen] = 0;
		     }
		     else
		          System.out.println("Error:lexeme is too long");

	}
	public static void getNonBlank() throws IOException {
		// TODO Auto-generated method stub
	     while(Character.isSpaceChar(nextChar))
	           getChar();

		
		
	}
	public static void getChar() throws IOException {
		// TODO Auto-generated method stub
		
		int i;
		
            		
			if((i=br.read())!=EOF )
			{
			
				nextChar=(char)i;
				if(Character.isAlphabetic(nextChar))
				{
					charClass=LETTER;
				
				}
				else if(Character.isDigit(nextChar))
				{
					
					charClass=DIGIT;
				}
				else
				{
					charClass=UNKNOWN;
				
				}
			}
		    
		
		
		else
		{
		
			charClass=EOF;
		}
		
	}
	
          
}

