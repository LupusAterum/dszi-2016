/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.amu.dszi.abstractClasses;

import pl.edu.amu.dszi.model.field.Field;

/**
 *
 * @author Karol Mazurek <kmazurek93@gmail.com>
 */
public interface FieldPriorityHandler {
    
    public Double getFieldPriority(Field field, Location2D location);
    
}
