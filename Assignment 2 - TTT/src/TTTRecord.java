public class TTTRecord {
	
	private String config;
	private int score, level;
	
	//constructor
	public TTTRecord(String config1, int score1, int level1)
	{
		config = config1;
		score = score1;
		level = level1;
	}
	
	//getter for configuration
	public String getConfiguration()
	{
		return config;
	}
	
	//getter for score
	public int getScore()
	{
		return score;
	}
	
	//getter for level
	public int getLevel()
	{
		return level;
	}
	

}
