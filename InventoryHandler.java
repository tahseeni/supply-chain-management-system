import java.util.*;

public class InventoryHandler {
	
    ArrayList <ArrayList<Furniture>> combinations;

    InventoryHandler(ArrayList<Furniture> furnitureData){
        this.combinations = new ArrayList <ArrayList<Furniture>>();
        this.combinations = generateCombinationsWithoutExcess(generateCombinations(furnitureData));
    }
    public ArrayList <ArrayList<Furniture>> generateCombinations(ArrayList <Furniture> f)
    {
		ArrayList <ArrayList <Furniture>> frnt = new ArrayList<ArrayList <Furniture>>();
		ArrayList<Furniture> ft;
        boolean found = false;
		int n = f.get(0).getParts().length;
			if(n == 1)
			{
				for(int i = 0; i < f.size(); i++) 
				{
					if(f.get(i).getParts()[n-1] == 'Y')
					{
						found = true;
					}
					
					if(found == true) 
					{
						ft = new ArrayList<Furniture>();
						ft.add(f.get(i));
						frnt.add(ft);
					}
					
					found = false;
				}
				
			}
			
			else if(n == 2)
			{
				for(int i = 0; i < f.size(); i++) 
				{
					if(f.get(i).getParts()[n-2] == 'Y' && f.get(i).getParts()[n-1] == 'Y')
					{
						found = true;
					}
					
					if(found == true) 
					{
						ft = new ArrayList<Furniture>();
						ft.add(f.get(i));
						frnt.add(ft);
					}
					else 
					{
						for(int j = i + 1; j < f.size(); j++) 
						{
							if((f.get(i).getParts()[n-2] == 'Y' || f.get(j).getParts()[n-2] == 'Y') && (f.get(i).getParts()[n-1] == 'Y' || f.get(j).getParts()[n-1] == 'Y'))
							{
								found = true;
							}

							if(found == true) 
							{
								ft = new ArrayList<Furniture>();
								ft.add(f.get(i));
								ft.add(f.get(j));
								frnt.add(ft);
							}
							found = false;
						}
					}
					found = false;
				}
			}
			else if(n == 3)
			{
						
				for(int i = 0; i < f.size(); i++) 
				{
					if(f.get(i).getParts()[n-3] == 'Y' && f.get(i).getParts()[n-2] == 'Y' && f.get(i).getParts()[n-1] == 'Y')
					{
						found = true;
					}
				
					if(found == true) 
					{
						ft = new ArrayList<Furniture>();
						ft.add(f.get(i));
						frnt.add(ft);
					}
					else 
					{
						if(i < i + 1) 
						{
							for(int j = i + 1; j < f.size(); j++) 
							{
								if((f.get(i).getParts()[n-3] == 'Y' || f.get(j).getParts()[n-3] == 'Y') && (f.get(i).getParts()[n-2] == 'Y' || f.get(j).getParts()[n-2] == 'Y')
							&& (f.get(i).getParts()[n-1] == 'Y' || f.get(j).getParts()[n-1] == 'Y'))
								{
									found = true;
								}

								if(found == true) 
								{
									ft = new ArrayList<Furniture>();
									ft.add(f.get(i));
									ft.add(f.get(j));
									frnt.add(ft);
								}
								else
								{
									if(j < i + 2) 
									{
										for(int k = i + 2; k < f.size(); k++) 
										{
											if((f.get(i).getParts()[n-3] == 'Y' || f.get(j).getParts()[n-3] == 'Y' || f.get(k).getParts()[n-3] == 'Y') 
													&& (f.get(i).getParts()[n-2] == 'Y' || f.get(j).getParts()[n-2] == 'Y'|| f.get(k).getParts()[n-2] == 'Y')
													&& (f.get(i).getParts()[n-1] == 'Y' || f.get(j).getParts()[n-1] == 'Y'|| f.get(k).getParts()[n-1] == 'Y'))
											{
												found = true;
											}

											if(found == true) 
											{
												ft = new ArrayList<Furniture>();
												ft.add(f.get(i));
												ft.add(f.get(j));
												ft.add(f.get(k));
												frnt.add(ft);
											}
											found = false;
										}
									}
								}
								found = false;
							}
						}
					}
					found = false;
				}
			}
			else if(n == 4)
			{
				for(int i = 0; i < f.size(); i++) 
				{
					if(f.get(i).getParts()[n-4] == 'Y' && f.get(i).getParts()[n-3] == 'Y' && f.get(i).getParts()[n-2] == 'Y' && f.get(i).getParts()[n-1] == 'Y')
					{
						found = true;
					}
				
					if(found == true) 
					{
						ft = new ArrayList<Furniture>();
						ft.add(f.get(i));
						frnt.add(ft);
					}
					else 
					{
						if(i < i + 1) 
						{
							for(int j = i + 1; j < f.size(); j++) 
							{
								if((f.get(i).getParts()[n-4] == 'Y' || f.get(j).getParts()[n-4] == 'Y') && (f.get(i).getParts()[n-3] == 'Y' || f.get(j).getParts()[n-3] == 'Y')
							&& (f.get(i).getParts()[n-2] == 'Y' || f.get(j).getParts()[n-2] == 'Y') && (f.get(i).getParts()[n-1] == 'Y' || f.get(j).getParts()[n-1] == 'Y'))
								{
									found = true;
								}

								if(found == true) 
								{
									ft = new ArrayList<Furniture>();
									ft.add(f.get(i));
									ft.add(f.get(j));
									frnt.add(ft);
								}
								else
								{
									if(j < i + 2) 
									{
										for(int k = i + 2; k < f.size(); k++) 
										{
											if((f.get(i).getParts()[n-4] == 'Y' || f.get(j).getParts()[n-4] == 'Y' || f.get(k).getParts()[n-4] == 'Y') 
													&& (f.get(i).getParts()[n-3] == 'Y' || f.get(j).getParts()[n-3] == 'Y'|| f.get(k).getParts()[n-3] == 'Y')
													&& (f.get(i).getParts()[n-2] == 'Y' || f.get(j).getParts()[n-2] == 'Y' || f.get(k).getParts()[n-2] == 'Y')
													&& (f.get(i).getParts()[n-1] == 'Y' || f.get(j).getParts()[n-1] == 'Y' || f.get(k).getParts()[n-1] == 'Y'))
											{
												found = true;
											}

											if(found == true) 
											{
												ft = new ArrayList<Furniture>();
												ft.add(f.get(i));
												ft.add(f.get(j));
												ft.add(f.get(k));
												frnt.add(ft);
											}
											else
											{
												if(k < i + 3)
												{
													for(int l = i + 3; l < f.size(); l++)
													{
														if((f.get(i).getParts()[n-4] == 'Y' || f.get(j).getParts()[n-4] == 'Y' || f.get(k).getParts()[n-4] == 'Y' || f.get(l).getParts()[n-4] == 'Y') 
																&& (f.get(i).getParts()[n-3] == 'Y' || f.get(j).getParts()[n-3] == 'Y'|| f.get(k).getParts()[n-3] == 'Y' || f.get(l).getParts()[n-3] == 'Y')
																&& (f.get(i).getParts()[n-2] == 'Y' || f.get(j).getParts()[n-2] == 'Y' || f.get(k).getParts()[n-2] == 'Y' || f.get(l).getParts()[n-2] == 'Y')
																&& (f.get(i).getParts()[n-1] == 'Y' || f.get(j).getParts()[n-1] == 'Y' || f.get(k).getParts()[n-1] == 'Y' || f.get(l).getParts()[n-1] == 'Y'))
														{
															found = true;
														}
														
														if(found == true) 
														{
															ft = new ArrayList<Furniture>();
															ft.add(f.get(i));
															ft.add(f.get(j));
															ft.add(f.get(k));
															ft.add(f.get(l));
															frnt.add(ft);
														}
														found = false;
													}
												}
											}
											found = false;
										}
									}
								}
								found = false;
							}
						}
					}
					found = false;
				}
			}
			return frnt;
    }
	
	public int findCheapest(ArrayList <ArrayList <Furniture>> frnt)
	{
		int min = 0;
		int p1 = 0;
		int p2 = 0;

		for(int j = 0; j < frnt.get(0).size(); j++)
		{
			p1 += frnt.get(0).get(j).getPrice();
		}

		for(int j = 0; j < frnt.size(); j++)
		{
			for(int k = 0; k < frnt.get(j).size(); k++)
			{
				p2 += frnt.get(j).get(k).getPrice();
			}
			if(p2 == p1)
			{
				if(frnt.get(j).size() < frnt.get(min).size())
				{
					min = j;
					p1 = 0;
					for(int l = 0; l < frnt.get(min).size(); l++)
					{
						p1 += frnt.get(min).get(l).getPrice();
					}
				}
			}
			else if(p2 < p1)
			{
				min = j;
				p1 = 0;
				for(int l = 0; l < frnt.get(min).size(); l++)
				{
					p1 += frnt.get(min).get(l).getPrice();
				}
			}
			p2 = 0;
		}
		return min;
	}

	public boolean removeExcessCheck(ArrayList <ArrayList <Furniture>> frnt)
	{
		int k = 0;
		boolean found = false;
		for(int i = 0; i < frnt.size(); i++)
		{
			for(int j = 0; j < frnt.size(); j++)
			{
				if(frnt.get(i).size() < frnt.get(j).size())
				{
					for(k = 0; k < frnt.get(i).size(); k++)
					{
						if(!frnt.get(j).contains(frnt.get(i).get(k)))
						{
							break;
						}
					}
					if(k == frnt.get(i).size())
					{
						found = true;
						return found;
					}
				}
			}
		}
		return found;
	}

	public int removeExcess(ArrayList <ArrayList <Furniture>> frnt)
	{
		int k;
		int index = 0;
		for(int i = 0; i < frnt.size(); i++)
		{
			for(int j = 0; j < frnt.size(); j++)
			{
				if(frnt.get(i).size() < frnt.get(j).size())
				{
					for(k = 0; k < frnt.get(i).size(); k++)
					{
						if(!frnt.get(j).contains(frnt.get(i).get(k)))
						{
							break;
						}
					}
					if(k == frnt.get(i).size())
					{
						index = j;
						return index;
					}
				}
			}
		}
		return index;
	}

	public ArrayList <ArrayList <Furniture>> generateCombinationsWithoutExcess(ArrayList <ArrayList <Furniture>> f)
	{
		while(removeExcessCheck(f))
		{
			f.remove(removeExcess(f));
		}
		return f;
	}

    public void displayCombinations(ArrayList <ArrayList<Furniture>> f)
    {
        for(int i = 0; i < f.size(); i++)
		{
			System.out.print("Combination " + i + ": ");
			for(int j = 0; j < f.get(i).size(); j++)
			{
				System.out.print(f.get(i).get(j).getID() + " ");
				System.out.print(" $" + f.get(i).get(j).getPrice() + " ");
			}
			System.out.println();
		}
    }

    public void displayCheapestCombination(ArrayList <ArrayList<Furniture>> f)
    {
        int index = findCheapest(f);
        int total = 0;
		System.out.println("Cheapest combination occurs at index " + index + ".");
		for(int i = 0; i < f.get(index).size(); i++)
		{
			total += f.get(index).get(i).getPrice();
		}
		System.out.println("Cheapest combination will cost: $" + total + ".");
    }
}