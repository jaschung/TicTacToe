
public class BlockedTicTacToe {
	
	private char[][] gameboard; 
	private int size;
	private int inline;
	private int levels;
	private int prevRow, prevCol;
	
	
	//constructor
	public BlockedTicTacToe(int board_size, int inline1, int max_levels1)
	{
		size = board_size;
		inline = inline1;
		levels = max_levels1;
		//moves = 0;
		
		gameboard = new char[size][size];
		
		//makes every position in the game board empty space (' ')
		for(int i = 0; i < gameboard.length; i++)
		{
			for (int j = 0; j < gameboard[i].length; j++)
			{
				gameboard[i][j] = ' ';
			}
		}
	}
	
	//creates and returns a new TTTDictionary object
	public TTTDictionary createDictionary()
	{
		TTTDictionary dict = new TTTDictionary(size);
		return dict;
	}
	
	//checks if a given string configuration is already in the dictionary
	public int repeatedConfig(TTTDictionary configurations)
	{
		String thisConfig = "";
		
		//first, appends every character position of the gameboard into a string thisConfig
		for(int i = 0; i < gameboard.length; i++)
		{
			for (int j = 0; j < gameboard[i].length; j++)
			{
				thisConfig += gameboard[j][i];
			}
		}
		
		//checks if the generated string is in the dictionary
		if(configurations.get(thisConfig) != null)
		{
			//returns the config's score
			return configurations.get(thisConfig).getScore();
		}
		else
		{
			//returns -1 if the config is not in the dictionary already
			return -1;
		}
	}
	
	//inserts a new TTTrecord into the dictionary
	public void insertConfig(TTTDictionary configurations, int score, int level)
	{
		String thisConfig = "";
		
		//again, we make a new configuration called thisConfig and append each position to it
		for(int i = 0; i < gameboard.length; i++)
		{
			for (int j = 0; j < gameboard[i].length; j++)
			{
				thisConfig += gameboard[j][i];
			}
		}
		
		TTTRecord record = new TTTRecord(thisConfig, score, level);
		
		try
		{
			//tries to add the new TTTRecord, if the dictionary doesn't already have the configuration
			configurations.put(record);
		}
		
		catch (DuplicatedKeyException e)
		{
			//throws excpetion if the config is already in the dictionary
			System.out.println(e);
		}
	}
	
	//stores given symbol into given position on gameboard
	public void storePlay(int row, int col, char symbol)
	{
		//saves the position of the most recent position of gameboard
		prevRow = row;
		prevCol = col;
		//stores symbol in position of gameboard
		gameboard[row][col] = symbol;
	}
	
	//checks if the given position on gameboard is empty
	public boolean squareIsEmpty (int row, int col)
	{
		if (gameboard[row][col] == ' ')
		{
			return true;
		}
		return false;
	}
	
	//checks if computer/human has won vertically, horizontally, diagonally
	public boolean wins (char symbol)
	{
		// checks horizontal winner
		for (int i = 0; i < gameboard.length; i++) 
		{
			// if at least one of the horizontal tiles is not the symbol, break loop
			if (gameboard[prevRow][i] != symbol) 
			{
				break;
			}

			// if we reached the end of the board without breaking loop, return true for a win
			if (i == size - 1) 
			{
				return true;
			}
		}

		// checks vertical winner
		for (int i = 0; i < gameboard[i].length; i++) 
		{
			// if at least one of the verical tiles is not the symbol, break loop
			if (gameboard[i][prevCol] != symbol) 
			{
				break;
			}

			// if reached end of board without breaking loop, return true for win
			if (i == size - 1) 
			{
				return true;
			}
		}

		//checks diagonal winner
		for (int i = 0; i < gameboard.length; i++) 
		{
			//if at least one of the diagonal tiles is not the symbol, break loop
			if (gameboard[i][i] != symbol) 
			{
				break;
			}

			// if reached end of board without breaking loop, return true for win
			if (i == size - 1) {
				return true;
			}
		}
        
		//checks the other diagonal winner
		for (int i = 0; i < gameboard[i].length; i++) 
		{
			//if at least one of the other diagonal tiles is not the symbol, break loop
			if (gameboard[i][(size - 1) - i] != symbol) 
			{
				break;
			}

			// if reached end of board without breaking loop, return true for win
			if (i == size - 1) 
			{
				return true;
			}
		}
		
		//returns false if no winner was found
		return false;
	}
	
	//checks if the gameboard is at a draw without a winner
	public boolean isDraw()
	{
		//checks entire gameboard
		for (int i = 0; i < gameboard.length; i++)
		{
			for (int j = 0; j < gameboard[i].length; j++)
			{
				//if there exists at least one more spot left to go on, return false
				if (gameboard[i][j] == ' ')
				{
					return false;
				}
			}
		}
		//return true if no more spots are empty
		return true;
	}
	
	//evaluates the game board for current situation
	public int evalBoard()
	{
		//if the gameboard is at a draw
		if (isDraw())
		{
			return 1;
		}
		
		//if the human wins
		else if (wins('x'))
		{
			return 0;
		}
		
		//if the computer wins
		else if (wins('o'))
		{
			return 3;
		}
		
		//if the game can continue
		return 2;
	}
}
