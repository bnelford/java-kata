package jsonParsingPackage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonTransformer {

	public static void main(String[] args) throws IOException {
		
		//read in the file from the first argument
		StringBuffer sb = new StringBuffer();
		BufferedReader br = null;
		try{
			br = new BufferedReader(new FileReader(args[0]));
			while(br.ready()) {
				sb.append(br.readLine());
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			br.close();
		}

		String jsonStringRaw = sb.toString();
		JSONArray inputArray = new JSONArray();
		try {
			inputArray = new JSONArray(jsonStringRaw);
		} 
		catch (JSONException e) {
			e.printStackTrace();
		}
		JSONArray outputArray = new JSONArray();
		//recursive method to get all the children starting with root
		outputArray = AllMyChildren("root", inputArray);

		//Output to file specified by second argument
		PrintWriter out = new PrintWriter(new FileWriter(args[1])); 
		try {
			out.print(outputArray.toString(4));
		} 
		catch (JSONException e) {
			e.printStackTrace();
		} 
		out.close();
	}

	//Recursive static method to find all the children of the stated object in the remaining array
	public static JSONArray AllMyChildren(String parent, JSONArray input) {
		
		//System.out.println("Looking for children of " + parent + "...");
		JSONArray children = new JSONArray();
		
		//Loop through items in the array
		for (int i = 0; i<input.length(); i++) {
			JSONObject obj = new JSONObject();
			try {
				obj = input.getJSONObject(i);
				System.out.println("	Examining object named: " + obj.getString("name"));
				if(obj.getString("parent").equalsIgnoreCase(parent)) {
					System.out.println("***Found child of " + parent + ": " + obj.getString("name"));
					
					//If it is a file, just add it to the parent
					if (obj.getString("type").equalsIgnoreCase("file")) {
						System.out.println("Child is file, adding to children of "  + parent);
						children.put(obj);
					}
					else {//If it is a folder, get its children, add them to parent object
						System.out.println("Child is folder, adding to children of " + parent + " and finding children... ");
						children.put(obj);
						JSONArray newInput = input;
						newInput.remove(i); //remove current object from the array that will be searched for its own children
						
						//recursive call to current object, looking for children in smaller array
						obj.put("children", AllMyChildren(obj.getString("name"), newInput));
						
						if (obj.get("children") == null) {
							System.out.println(parent + " doesn't have any children, returning empty");
							obj.remove("children"); //if there are no children of this node
						}
					}
				}
				else {  //If it doesn't have a matching parent, move to the next item in the input
					System.out.println("		" + obj.getString("name") + " isn't the child of " + parent + ", examining next item...");
					continue;
				}
				
			} 
			catch (JSONException e) 
			{
				e.printStackTrace();
				continue;
			}
		}
		//base case, found all children
		System.out.println("Done looking for children of " + parent + " and found " + children.length() + " children");
		
		//If there are no children, remove the children array from the parent
		if (children.length() == 0) {
			return null;
		}
		
		System.out.println("Returning children of: " + parent + ": " + children.toString());
		return children; 
	}
}
