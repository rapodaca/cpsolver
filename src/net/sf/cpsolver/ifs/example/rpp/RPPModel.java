package net.sf.cpsolver.ifs.example.rpp;

import java.util.*;

import net.sf.cpsolver.ifs.model.*;

/**
 * RPP model.
 * <br><br>
 * The random placement problem (RPP; for more details, see <a href='http://www.fi.muni.cz/~hanka/rpp/'>http://www.fi.muni.cz/~hanka/rpp/</a>) 
 * seeks to place a set of randomly generated rectangles (called objects) of different sizes into a larger rectangle
 * (called placement area) in such a way that no objects overlap and all objects� borders are parallel to the border 
 * of the placement area. In addition, a set of allowable placements can be randomly generated for each object. 
 * The ratio between the total area of all objects and the size of the placement area will be denoted as the filled area ratio.
 * <br><br>
 * RPP allows us to generate various instances of the problem similar to a trivial timetabling problem. The correspondence is 
 * as follows: the object corresponds to a course to be timetabled � the x-coordinate to its time, the y-coordinate to its classroom. 
 * For example, a course taking three hours corresponds to an object with dimensions 3x1 (the course should be taught in one 
 * classroom only).  Each course can be placed only in a classroom of sufficient capacity � we can expect 
 * that the classrooms are ordered increasingly in their size so each object will have a lower bound on its y-coordinate.
 * 
 * @version
 * IFS 1.1 (Iterative Forward Search)<br>
 * Copyright (C) 2006 Tomas Muller<br>
 * <a href="mailto:muller@unitime.org">muller@unitime.org</a><br>
 * Lazenska 391, 76314 Zlin, Czech Republic<br>
 * <br>
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * <br><br>
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * <br><br>
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */
public class RPPModel extends Model {
    /** Constructor. */
    public RPPModel() {
        super();
    }
    
    /** Returns rectangle of the given name */
    public Rectangle getRectangle(String name) {
        for (Enumeration i = variables().elements(); i.hasMoreElements();) {
            Variable v = (Variable)i.nextElement();
            if (v instanceof Rectangle && name.equals(((Rectangle)v).getName()))
                return (Rectangle)v;
        }
        return null;
    }
    
}
