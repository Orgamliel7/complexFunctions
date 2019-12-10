package Ex1;

import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

//import com.google.gson.Gson;
//import com.google.gson.stream.JsonReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Functions_GUI implements functions
{
	ArrayList<function> G_Functions;
	public Functions_GUI()
	{
		G_Functions = new ArrayList<function>();
	}
	@Override
	public int size() 
	{
		// TODO Auto-generated method stub
		return G_Functions.size();
	}

	@Override
	public boolean isEmpty() 
	{
		// TODO Auto-generated method stub
		return G_Functions.isEmpty();
	}

	@Override
	public boolean contains(Object o) 
	{
		// TODO Auto-generated method stub
		return G_Functions.contains(o);
	}

	@Override
	public Iterator<function> iterator() 
	{
		// TODO Auto-generated method stub
		return G_Functions.iterator();
	}

	@Override
	public Object[] toArray() 
	{
		// TODO Auto-generated method stub
		return G_Functions.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) 
	{
		// TODO Auto-generated method stub
		return G_Functions.toArray(a);
	}

	@Override
	public boolean add(function e) 
	{
		// TODO Auto-generated method stub
		return G_Functions.add(e);
	}

	@Override
	public boolean remove(Object o) 
	{
		// TODO Auto-generated method stub
		return G_Functions.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) 
	{
		// TODO Auto-generated method stub
		return G_Functions.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends function> c) 
	{
		// TODO Auto-generated method stub
		return G_Functions.addAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c) 
	{
		// TODO Auto-generated method stub
		return G_Functions.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) 
	{
		// TODO Auto-generated method stub
		return G_Functions.retainAll(c);
	}

	@Override
	public void clear() 
	{
		// TODO Auto-generated method stub
		G_Functions.clear();
	}

	@Override
	public void initFromFile(String file) throws IOException 
	{
		// TODO Auto-generated method stub
		G_Functions = new ArrayList<function>();
		BufferedReader reader;
		reader = new BufferedReader(new FileReader(file));
		String line = reader.readLine();
		while(line != null)
		{
			line = line.substring(line.indexOf("f(x)=")+"f(x)=".length());
			line = line.strip(); // Removes white spaces
			ComplexFunction cf = new ComplexFunction();
			G_Functions.add(cf.initFromString(line));
			line = reader.readLine();
		}
		reader.close();

	}
	public function get(int index)
	{
		return G_Functions.get(index);
	}

	@Override
	public void saveToFile(String file) throws IOException 
	{
		FileWriter fw = new FileWriter(file);
		Iterator<function> itrFunction = G_Functions.iterator();
		int counter = 0;
		while(itrFunction.hasNext())
		{
			function f = itrFunction.next();
			fw.write(counter+") "+"   f(x)= "+f.toString()+'\n');
			counter++;
		}
		fw.close();

	}

	@Override
	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) 
	{
		// TODO Auto-generated method stub
		//StdDraw.enableDoubleBuffering();

		StdDraw.setCanvasSize(width, height);
		StdDraw.setXscale(rx.get_min(), rx.get_max());
		StdDraw.setYscale(ry.get_min(), ry.get_max());

		StdDraw.setPenColor(Color.LIGHT_GRAY);

		// Vertical Lines
		for (double i = rx.get_min(); i <= rx.get_max(); i++) 
		{
			StdDraw.line(i, ry.get_min(), i, ry.get_max());
		}
		// Horizontal Lines
		for (double i = ry.get_min(); i <= ry.get_max(); i++) 
		{
			StdDraw.line(rx.get_min(), i, rx.get_max(), i);
		}

		StdDraw.setPenColor(Color.BLACK);
		StdDraw.setPenRadius(0.005);
		
		StdDraw.setFont(new Font("TimesRoman", Font.BOLD, 15));
		StdDraw.line(rx.get_min(), 0, rx.get_max(), 0);

		
		for (double i = rx.get_min(); i <= rx.get_max(); i++) 
		{
			StdDraw.text(i, -0.30, Integer.toString(Math.toIntExact((long) i)));
		}
		StdDraw.line(0, ry.get_min(), 0, ry.get_max());

		
		for (double i = ry.get_min(); i <= ry.get_max(); i++) 
		{
			StdDraw.text(-0.20,i, Integer.toString(Math.toIntExact((long) i)));
		}

		for (function function : G_Functions) 
		{
			ArrayList<Double> xTag = new ArrayList<Double>();
			ArrayList<Double> yTag = new ArrayList<Double>();
			
		
			double rx_step = (Math.abs(rx.get_min())+Math.abs(rx.get_max()))/resolution;
			for (double i = rx.get_min(); i < rx.get_max(); i+=rx_step)
			{
				xTag.add(i);
				yTag.add(function.f(i));
			}
			int R = (int)(Math.random()*256);
			int G = (int)(Math.random()*256);
			int B = (int)(Math.random()*256);
			Color color = new Color(R, G, B);
			StdDraw.setPenColor(color);
			for (int i = 0; i < xTag.size()-1; i++) 
			{
				StdDraw.line(xTag.get(i), yTag.get(i), xTag.get(i+1), yTag.get(i+1));
			}
		}
		StdDraw.setPenColor(Color.BLACK);
	}

	@Override
	public void drawFunctions(String json_file) 
	{
		//json simple
		// TODO Auto-generated method stub
		try 
		{
			JSONObject  obj = (JSONObject) new JSONParser().parse(new FileReader(json_file));
			int Width=1000;
			int Height=600;
			int Resolution=200;
			double[] Range_X = {-10,10};
			double[] Range_Y = {-5,15};
			JSONArray JAx;
			JSONArray JAy;
			if(obj.get("Width") != null)
			{
				Width = Math.toIntExact((long) obj.get("Width"));
			}
			if(obj.get("Height") != null)
			{
				Height = Math.toIntExact((long) obj.get("Height"));
			}
			if(obj.get("Resolution") != null)
			{
				Resolution = Math.toIntExact((long) obj.get("Resolution"));
			}
			if(obj.get("Range_X") != null)
			{
				JAx = (JSONArray) obj.get("Range_X");
				Iterator<Long> itr = JAx.iterator();
				int i=0;
				while(itr.hasNext() && i<2)
				{
					
					Range_X[i++] = (double)itr.next().doubleValue();
				}
			}
			if(obj.get("Range_Y") != null)
			{
				JAy = (JSONArray) obj.get("Range_Y");
				Iterator<Long> itr = JAy.iterator();
				int i=0;
				while(itr.hasNext() && i<2)
				{
					Range_Y[i++] = (double)itr.next().doubleValue();
				}
			}
			Range rx = new Range(Range_X[0], Range_X[1]);
			Range ry = new Range(Range_Y[0], Range_Y[1]);
			drawFunctions(Width,Height,rx,ry,Resolution);
			//obj.get
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
		
		/*
		Gson gson = new Gson();
		JsonReader reader = null;
		try 
		{
			reader = new JsonReader(new FileReader(json_file));
		} catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		G_Functions = gson.fromJson(reader, function.class); // contains the whole reviews list
		G_Functions.toString(); 
		*/
		
	}

}