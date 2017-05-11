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

import isaac.math.Evaluatable;
import isaac.math.Numeric;
import isaac.math.Vector;

public class Vertex<T extends Evaluatable> extends Vector<T> {
	
	public Vertex(){
		super(3);
	}
	
	public Vertex(T x, T y, T z){
		super();
		add(x);
		add(y);
		add(z);
	}
	
	@SuppressWarnings("unchecked")
	public Vertex(double x, double y, double z){
		super();
		add((T)new Numeric(x));
		add((T)new Numeric(y));
		add((T)new Numeric(z));
	}
	
	public T x(){
		return this.get(0);
	}
	
	public T y(){
		return this.get(1);
	}
	
	public T z(){
		return this.get(2);
	}
	
	public void setX(T x){
		set(0,x);
	}
	
	public void setY(T y){
		set(1,y);
	}
	
	public void setZ(T z){
		set(2,z);
	}
	
	public Vertex<T> cross(Vertex<T> vertex){
		
		double x = y().get()*vertex.z().get() - z().get()*vertex.y().get();
		double y = z().get()*vertex.x().get() - x().get()*vertex.z().get();
		double z = x().get()*vertex.y().get() - y().get()*vertex.x().get();
		Vertex<T> result = new Vertex<T>(x,y,z);
		return result;
	}
	
	public double distanceTo(Vertex<T> vertex){
		return Math.sqrt((x().get()-vertex.x().get())*(x().get()-vertex.x().get()) + 
				(y().get()-vertex.y().get())*(y().get()-vertex.y().get()) +
				(z().get()-vertex.z().get())*(z().get()-vertex.z().get()));
	}

}
