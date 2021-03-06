package net.sf.cpsolver.studentsct.filter;

import net.sf.cpsolver.studentsct.model.AcademicAreaCode;
import net.sf.cpsolver.studentsct.model.Student;

/**
 * This student filter accepts only freshman students.
 * 
 * @version StudentSct 1.2 (Student Sectioning)<br>
 *          Copyright (C) 2007 - 2010 Tomas Muller<br>
 *          <a href="mailto:muller@unitime.org">muller@unitime.org</a><br>
 *          <a href="http://muller.unitime.org">http://muller.unitime.org</a><br>
 * <br>
 *          This library is free software; you can redistribute it and/or modify
 *          it under the terms of the GNU Lesser General Public License as
 *          published by the Free Software Foundation; either version 3 of the
 *          License, or (at your option) any later version. <br>
 * <br>
 *          This library is distributed in the hope that it will be useful, but
 *          WITHOUT ANY WARRANTY; without even the implied warranty of
 *          MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 *          Lesser General Public License for more details. <br>
 * <br>
 *          You should have received a copy of the GNU Lesser General Public
 *          License along with this library; if not see
 *          <a href='http://www.gnu.org/licenses/'>http://www.gnu.org/licenses/</a>.
 */

public class FreshmanStudentFilter implements StudentFilter {

    public FreshmanStudentFilter() {
    }

    /**
     * Accept student. Student is accepted if it is freshmen, i.e., academic
     * area classification code is A, 01, or 02.
     **/
    @Override
    public boolean accept(Student student) {
        for (AcademicAreaCode aac : student.getAcademicAreaClasiffications()) {
            if ("A".equals(aac.getCode()))
                return true; // First Year
            if ("01".equals(aac.getCode()))
                return true; // First Semester Freshman
            if ("02".equals(aac.getCode()))
                return true; // Second Semester Freshman
        }
        return false;
    }

}
