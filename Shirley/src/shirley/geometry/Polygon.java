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

import isaac.math.LinearMath;
import isaac.math.Matrix;
import isaac.math.Numeric;

public interface Polygon {

	public Vertex vertex(int index);

	public int numVertices();

	public default Vertex unitNormal() {
		Matrix matrix1 = new Matrix(3, 3);
		Matrix matrix2 = new Matrix(3, 3);
		Matrix matrix3 = new Matrix(3, 3);

		matrix1.set(0, 0, new Numeric(1));
		matrix1.set(0, 1, vertex(0).y());
		matrix1.set(0, 2, vertex(0).z());
		matrix1.set(1, 0, new Numeric(1));
		matrix1.set(1, 1, vertex(1).y());
		matrix1.set(1, 2, vertex(1).z());
		matrix1.set(2, 0, new Numeric(1));
		matrix1.set(2, 1, vertex(2).y());
		matrix1.set(2, 2, vertex(2).z());

		matrix2.set(0, 0, vertex(0).x());
		matrix2.set(0, 1, new Numeric(1));
		matrix2.set(0, 2, vertex(0).z());
		matrix2.set(1, 0, vertex(1).x());
		matrix2.set(1, 1, new Numeric(1));
		matrix2.set(1, 2, vertex(1).z());
		matrix2.set(2, 0, vertex(2).x());
		matrix2.set(2, 1, new Numeric(1));
		matrix2.set(2, 2, vertex(2).z());

		matrix3.set(0, 0, vertex(0).x());
		matrix3.set(0, 1, vertex(0).y());
		matrix3.set(0, 2, new Numeric(1));
		matrix3.set(1, 0, vertex(1).x());
		matrix3.set(1, 1, vertex(1).y());
		matrix3.set(1, 2, new Numeric(1));
		matrix3.set(2, 0, vertex(2).x());
		matrix3.set(2, 1, vertex(2).y());
		matrix3.set(2, 2, new Numeric(1));

		double x = matrix1.determinant();
		double y = matrix2.determinant();
		double z = matrix3.determinant();

		double magnitude = Math.sqrt(x * x + y * y + z * z);

		return new Vertex(x / magnitude, y / magnitude, z / magnitude);

	}

	public default double area() {
		Vertex total = new Vertex();
		for(int i=0;i<numVertices();i++){
			Vertex v1 = vertex(i);
			Vertex v2;
			if(i==numVertices()-1){
				v2 = vertex(0);
			} else {
				v2 = vertex(i+1);
			}
			
			Vertex prod = v1.cross(v2);
			
			total.setX(new Numeric(total.x().get() + prod.x().get()));
			total.setY(new Numeric(total.y().get() + prod.y().get()));
			total.setZ(new Numeric(total.z().get() + prod.z().get()));			
		}
		return Math.abs(0.5*LinearMath.dotProduct(total, this.unitNormal()).get());
	}

}
