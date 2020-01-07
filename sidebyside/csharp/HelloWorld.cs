/*
 * Basic, minimal Hello World type program in Microsoft C#.
 * Note how most of C# was copied from Java, changing the
 * API names to allow M$ to claim they invented it.
 * Life would be much simpler if they'd just done a Java
 * compiler that emitted CLR codes...
 */

using System;	// like import

namespace SideBySide.CSharp {	// like package (and optional)

	public class MyClass {
		public static void Main() {
			Console.WriteLine("Hello, World");
		}
	} 
}
