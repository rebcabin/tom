/*
 * Copyright (c) 2004-2007, INRIA
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met: 
 * 	- Redistributions of source code must retain the above copyright
 * 	notice, this list of conditions and the following disclaimer.  
 * 	- Redistributions in binary form must reproduce the above copyright
 * 	notice, this list of conditions and the following disclaimer in the
 * 	documentation and/or other materials provided with the distribution.
 * 	- Neither the name of the INRIA nor the names of its
 * 	contributors may be used to endorse or promote products derived from
 * 	this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package tutorial;

import tutorial.cookbook.house.*;
import tutorial.cookbook.house.types.*;
import tom.library.sl.*;

public class Cookbook {
  
  public Cookbook() {
  }
  
  %gom { 
    module House
      imports String 
      
    public
      abstract syntax
      
      House = concRoom(Room*)
      Room = room(name: String, furniture: Furniture)
      Furniture = concPieceOfFurniture(PieceOfFurniture*)
      PieceOfFurniture = bed()
      | chair()
      | armchair()
      | fridge()
  }

  %include { sl.tom }

  public final static void main(String Args[]) {
    Cookbook ex = new Cookbook();
    ex.run();
  }
  private void run() {
    House myHouse = `concRoom(room("kitchen",concPieceOfFurniture(chair(),chair(),fridge())),room("bedroom",concPieceOfFurniture(bed(),chair())));
    Strategy seekChairs = `SeekChairs();
    Strategy replaceChairs = `ReplaceChairs();
    try {
      System.out.println("subject = " + myHouse);
      `BottomUp(seekChairs).fire(myHouse);
      myHouse = (House)`BottomUp(replaceChairs).fire(myHouse);
      System.out.println("replacing chairs...");
      System.out.println("bottumup new subject = " + myHouse);
    } catch (FireException e) {
      System.out.println("failed on = " + myHouse);
    }
  }
 
  %strategy SeekChairs() extends `Identity() {

    visit PieceOfFurniture {
      chair() -> { 
        System.out.println("1 Chair !");
      }
      armchair() -> {
        System.out.println("1 Armchair !");
      }
    }
  }

  %strategy ReplaceChairs() extends `Identity() {

    visit PieceOfFurniture {
      chair() -> { return `armchair(); }
    }
}
}
