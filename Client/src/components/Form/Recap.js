// eslint-disable-next-line import/no-named-as-default
import ReCAPTCHA from 'react-google-recaptcha';

function Recap() {
  // eslint-disable-next-line no-unused-vars
  const onChange = (value) => {};

  return (
    <div className="Recap">
      <ReCAPTCHA
        sitekey="6LeAKskiAAAAAEakbkKCcu_A-Lm5p0okXMuBBvPJ"
        onChange={onChange}
      />
    </div>
  );
}
export default Recap;
