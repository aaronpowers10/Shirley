/*
 *
 *  Copyright (C) 2018 Aaron Powers
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

public class TwoDPolygon implements Polygon{
	
	private ArrayList<Vertex> vertices;

	public TwoDPolygon(){
		vertices = new ArrayList<Vertex>();
	}
	
	public void add(double x, double y){
		vertices.add(new Vertex(x,y,0));
	}
	
	@Override
	public Vertex vertex(int index) {
		return vertices.get(index);
	}


	@Override
	public int numVertices() {
		return vertices.size();
	}

}
