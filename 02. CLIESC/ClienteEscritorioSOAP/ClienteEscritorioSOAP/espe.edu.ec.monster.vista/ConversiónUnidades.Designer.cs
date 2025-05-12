namespace ClienteEscritorioSOAP.espe.edu.ec.monster.vista
{
    partial class ConversiónUnidades
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(ConversiónUnidades));
            this.lblTexUni = new System.Windows.Forms.Label();
            this.lblSelecOp = new System.Windows.Forms.Label();
            this.lblValor = new System.Windows.Forms.Label();
            this.btnConvertir = new System.Windows.Forms.Button();
            this.cmbConversion = new System.Windows.Forms.ComboBox();
            this.lblResultado = new System.Windows.Forms.Label();
            this.txtValor = new System.Windows.Forms.TextBox();
            this.txtRest = new System.Windows.Forms.TextBox();
            this.btnSalir = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // lblTexUni
            // 
            this.lblTexUni.AutoSize = true;
            this.lblTexUni.Font = new System.Drawing.Font("Lucida Bright", 13.8F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblTexUni.Location = new System.Drawing.Point(126, 62);
            this.lblTexUni.Name = "lblTexUni";
            this.lblTexUni.Size = new System.Drawing.Size(291, 26);
            this.lblTexUni.TabIndex = 0;
            this.lblTexUni.Text = "Conversión de Unidades";
            this.lblTexUni.Click += new System.EventHandler(this.lblTexUni_Click);
            // 
            // lblSelecOp
            // 
            this.lblSelecOp.AutoSize = true;
            this.lblSelecOp.Location = new System.Drawing.Point(75, 164);
            this.lblSelecOp.Name = "lblSelecOp";
            this.lblSelecOp.Size = new System.Drawing.Size(147, 16);
            this.lblSelecOp.TabIndex = 1;
            this.lblSelecOp.Text = "Seleccione una opción:";
            this.lblSelecOp.Click += new System.EventHandler(this.lblSelecOp_Click);
            // 
            // lblValor
            // 
            this.lblValor.AutoSize = true;
            this.lblValor.Location = new System.Drawing.Point(117, 223);
            this.lblValor.Name = "lblValor";
            this.lblValor.Size = new System.Drawing.Size(105, 16);
            this.lblValor.TabIndex = 2;
            this.lblValor.Text = "Ingrese un valor:";
            this.lblValor.Click += new System.EventHandler(this.lblValor_Click);
            // 
            // btnConvertir
            // 
            this.btnConvertir.Location = new System.Drawing.Point(296, 268);
            this.btnConvertir.Name = "btnConvertir";
            this.btnConvertir.Size = new System.Drawing.Size(75, 23);
            this.btnConvertir.TabIndex = 3;
            this.btnConvertir.Text = "Convertir";
            this.btnConvertir.UseVisualStyleBackColor = true;
            this.btnConvertir.Click += new System.EventHandler(this.btnConvertir_Click);
            // 
            // cmbConversion
            // 
            this.cmbConversion.FormattingEnabled = true;
            this.cmbConversion.Location = new System.Drawing.Point(296, 161);
            this.cmbConversion.Name = "cmbConversion";
            this.cmbConversion.Size = new System.Drawing.Size(121, 24);
            this.cmbConversion.TabIndex = 4;
            this.cmbConversion.SelectedIndexChanged += new System.EventHandler(this.cmbConversion_SelectedIndexChanged);
            // 
            // lblResultado
            // 
            this.lblResultado.AutoSize = true;
            this.lblResultado.Location = new System.Drawing.Point(150, 356);
            this.lblResultado.Name = "lblResultado";
            this.lblResultado.Size = new System.Drawing.Size(72, 16);
            this.lblResultado.TabIndex = 5;
            this.lblResultado.Text = "Resultado:";
            this.lblResultado.Click += new System.EventHandler(this.lblResultado_Click);
            // 
            // txtValor
            // 
            this.txtValor.Location = new System.Drawing.Point(296, 220);
            this.txtValor.Name = "txtValor";
            this.txtValor.Size = new System.Drawing.Size(100, 22);
            this.txtValor.TabIndex = 6;
            this.txtValor.TextChanged += new System.EventHandler(this.txtValor_TextChanged);
            // 
            // txtRest
            // 
            this.txtRest.Location = new System.Drawing.Point(296, 350);
            this.txtRest.Name = "txtRest";
            this.txtRest.Size = new System.Drawing.Size(100, 22);
            this.txtRest.TabIndex = 7;
            this.txtRest.TextChanged += new System.EventHandler(this.txtRest_TextChanged);
            // 
            // btnSalir
            // 
            this.btnSalir.Location = new System.Drawing.Point(296, 425);
            this.btnSalir.Name = "btnSalir";
            this.btnSalir.Size = new System.Drawing.Size(75, 23);
            this.btnSalir.TabIndex = 8;
            this.btnSalir.Text = "Salir";
            this.btnSalir.UseVisualStyleBackColor = true;
            this.btnSalir.Click += new System.EventHandler(this.btnSalir_Click);
            // 
            // ConversiónUnidades
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackgroundImage = ((System.Drawing.Image)(resources.GetObject("$this.BackgroundImage")));
            this.ClientSize = new System.Drawing.Size(545, 482);
            this.Controls.Add(this.btnSalir);
            this.Controls.Add(this.txtRest);
            this.Controls.Add(this.txtValor);
            this.Controls.Add(this.lblResultado);
            this.Controls.Add(this.cmbConversion);
            this.Controls.Add(this.btnConvertir);
            this.Controls.Add(this.lblValor);
            this.Controls.Add(this.lblSelecOp);
            this.Controls.Add(this.lblTexUni);
            this.Name = "ConversiónUnidades";
            this.Text = "ConversiónUnidades";
            this.Load += new System.EventHandler(this.ConversiónUnidades_Load);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label lblTexUni;
        private System.Windows.Forms.Label lblSelecOp;
        private System.Windows.Forms.Label lblValor;
        private System.Windows.Forms.Button btnConvertir;
        private System.Windows.Forms.ComboBox cmbConversion;
        private System.Windows.Forms.Label lblResultado;
        private System.Windows.Forms.TextBox txtValor;
        private System.Windows.Forms.TextBox txtRest;
        private System.Windows.Forms.Button btnSalir;
    }
}