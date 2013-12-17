using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using ProtoBuf.Meta;
using System.Reflection;

namespace DLLToSerializer
{
    class Program
    {
        // args[0]=导出DLL的名称,args后面是dll的文件位置
        static void Main(string[] args)
        {
            if (args.Length < 2)
            {
                Console.WriteLine("输入的参数有错!");
                System.Environment.Exit(-1);
            }

            string sDLLNS = args[0];
            var models = TypeModel.Create();
            int iCount = args.Length;
            for (int i = 1; i < iCount; i++ )
            {
                Assembly assem = Assembly.LoadFrom(args[i]);
                var types = assem.GetTypes();
                foreach (var t in types)
                {
                    models.Add(t, true);
                }
            }
            models.Compile(sDLLNS, args[0] + ".dll");
            Console.WriteLine("Success");
            System.Environment.Exit(0);
        }
    }
}
