//------------------------------------------------------------------------------
// <auto-generated>
//     Этот код создан по шаблону.
//
//     Изменения, вносимые в этот файл вручную, могут привести к непредвиденной работе приложения.
//     Изменения, вносимые в этот файл вручную, будут перезаписаны при повторном создании кода.
// </auto-generated>
//------------------------------------------------------------------------------

namespace Gemo_test_application
{
    using System;
    using System.Collections.Generic;
    
    public partial class AnalyzesResults
    {
        public int id { get; set; }
        public Nullable<int> id_Patient { get; set; }
        public Nullable<int> id_Analyze { get; set; }
        public Nullable<decimal> Result { get; set; }
        public Nullable<System.DateTime> Date { get; set; }
    
        public virtual Analyzes Analyzes { get; set; }
        public virtual Patients Patients { get; set; }
    }
}