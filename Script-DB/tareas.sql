PGDMP                          y            tareas    12.1    12.1                 0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    32787    tareas    DATABASE     �   CREATE DATABASE tareas WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Spanish_Colombia.1252' LC_CTYPE = 'Spanish_Colombia.1252';
    DROP DATABASE tareas;
                adminTareas    false            �            1259    32788    tarea    TABLE     �   CREATE TABLE public.tarea (
    identificador bigint NOT NULL,
    descripcion character varying(255) NOT NULL,
    fecha_creacion timestamp without time zone NOT NULL,
    vigente boolean NOT NULL
);
    DROP TABLE public.tarea;
       public         heap    adminTareas    false            �
          0    32788    tarea 
   TABLE DATA           T   COPY public.tarea (identificador, descripcion, fecha_creacion, vigente) FROM stdin;
    public          adminTareas    false    202   �       ~
           2606    32792    tarea tarea_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY public.tarea
    ADD CONSTRAINT tarea_pkey PRIMARY KEY (identificador);
 :   ALTER TABLE ONLY public.tarea DROP CONSTRAINT tarea_pkey;
       public            adminTareas    false    202            �
      x������ � �     