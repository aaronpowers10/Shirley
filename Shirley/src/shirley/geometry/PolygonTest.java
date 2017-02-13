/*
 *
 *  Copyright (C) 2017 Aaron Powers
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
package shirley.geometry;

import java.util.ArrayList;

import isaac.math.Numeric;

public class PolygonTest implements Polygon<Numeric>{
	
	private ArrayList<Vertex<Numeric>> vertices;
	
	public PolygonTest(){
		vertices = new ArrayList<Vertex<Numeric>>();
	}
	
	public void addVertex(double x, double y, double z){
		vertices.add(new Vertex<Numeric>(x,y,z));
	}

	@Override
	public Vertex<Numeric> vertex(int index) {
		return vertices.get(index);
	}

	@Override
	public int numVertices() {
		return vertices.size();
	}

	public static void main(String[] args){
		PolygonTest polygon = new PolygonTest();
		polygon.addVertex(0, 0, 0);
		polygon.addVertex(111.8, 0, 0);
		polygon.addVertex(96.8, 15, 0);
		polygon.addVertex(15, 15, 0);
		
		System.out.println(polygon.area());
	}

}
