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
    
    public partial class ServicesForStudy
    {
        public int id { get; set; }
        public Nullable<int> id_Study { get; set; }
        public Nullable<int> id_Service { get; set; }
    
        public virtual Services Services { get; set; }
        public virtual Study Study { get; set; }
    }
}