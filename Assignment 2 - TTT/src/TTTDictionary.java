import java.util.LinkedList;

public class TTTDictionary implements TTTDictionaryADT
{
	private int size;
	LinkedList<TTTRecord>[] dict;
	private int numItems;
	
	//constructor for dictionary
	public TTTDictionary (int size1)
	{
		size = size1;
		dict = new LinkedList[size];
	}
	
	
	//puts the TTTrecord into dictionary
	public int put(TTTRecord record) throws DuplicatedKeyException
	{
		//gets a unique position for each record
		int position = hashFunction(record.getConfiguration());
		
		//if the unique position is already filled, then throw an exception
		if(get(record.getConfiguration()) != null)
		{
			throw new DuplicatedKeyException("Configuration is already is in the dictionary.");
		}
		
		//if the position in the LinkedList is current null, initialize it with a new LinkedList (separate chaining)
		if(dict[position] == null)
		{
			dict[position] = new LinkedList<TTTRecord>();
			//adds the TTTrecord to the unique position
			dict[position].add(record);
			return 0;
		}
		
		//the unique position already has a linkedlist for separate chaining, so add TTTrecord to it
		else
		{
			dict[position].add(record);
			return 1;
		}
	}

	
	//removes a configuration from the dictionary
	@Override
	public void remove(String config) throws InexistentKeyException {
		
		//again, gets the unique position of a string configuration
		int position = hashFunction(config);
		
		//if nothing is in the position, then throw an exception
		if(dict[position] == null)
		{
			throw new InexistentKeyException("Configuration is not in the dictionary.");
		}
		
		//look through the linkedlist (chain) in the unique position
		for(int i = 0; i < dict[position].size(); i++)
		{
			//if we find the configuration, set it to null
			if (dict[position].get(i).equals(config))
			{
				dict[position].set(i, null);
			}
		}
	}

	
	//gets a TTTrecord given the string configuration
	@Override
	public TTTRecord get(String config) {
		
		//again, gets a unique position for given configuration
		int position = hashFunction(config); 
		
		//if the position of the dictionary is empty
		if (dict[position] == null)
		{
			return null;
		}
		
		else
		{
			//look through the linkedlist (chain) in the unique position
			for(int i = 0; i < dict[position].size(); i++)
			{
				//if we find the given configuration, return it
				if(dict[position].get(i).getConfiguration().equals(config))
				{
					return dict[position].get(i);
				}
			}
			return null;
		}
	}


	//gets the number of elements in the dictionary
	@Override
	public int numElements() 
	{
		return numItems;
	}
	
	//provides a unique position for each string configuration
	public int hashFunction(String config)
	{
		int pos = 0;
		int prime = 113; 	//chose a prime number to generate less collisions
	        
		//loop through the length of the string configuration
	    for (int i = 0; i < config.length(); i++)
	    {
			char ch = config.charAt(i);
			//converts each character in the string to an int and sums it up, because no 2 strings have the same int value if they are different
			// so, each pos generated will be unique to each string config
			pos = (pos + (int)ch) * prime;
		}
	    
	    //sometimes the pos gets too large, and turns into a negative number. So this makes it back to positive
	    if(pos < 0)
	    {
	    	pos = prime - pos;
	    }
	    
	    //gives the position modulo by size to make sure pos is size-1 to stay in array bounds
		return pos%size;
	}
}
