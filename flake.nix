{
  outputs =
    { self, nixpkgs, ... }:
    let
      system = "aarch64-darwin";
      pkgs = import nixpkgs { inherit system; };
    in
    {
      devShells.${system}.default = pkgs.mkShell {
        buildInputs = with pkgs; [
          openjdk25
          maven
        ];
      
        shellHook = ''
            export JAVA_HOME=${pkgs.openjdk25}
        '';
      };
    };
}
