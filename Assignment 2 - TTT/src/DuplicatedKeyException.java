
public class DuplicatedKeyException extends Exception
{
	//returns message when the TTTrecord already exists in the dictionary
	public DuplicatedKeyException(String message) 
	{
		super(message);
	}
}
